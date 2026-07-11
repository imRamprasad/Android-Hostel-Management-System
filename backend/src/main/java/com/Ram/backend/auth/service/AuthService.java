package com.Ram.backend.auth.service;

import com.Ram.backend.auth.dto.AuthResponse;
import com.Ram.backend.auth.dto.FirebaseLoginRequest;
import com.Ram.backend.auth.dto.LoginRequest;
import com.Ram.backend.auth.dto.RegisterRequest;
import com.Ram.backend.auth.security.JwtService;
import com.Ram.backend.common.exception.BadRequestException;
import com.Ram.backend.user.dto.UserResponse;
import com.Ram.backend.user.entity.Role;
import com.Ram.backend.user.entity.User;
import com.Ram.backend.user.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FirebaseAuth firebaseAuth;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, 
                       java.util.Optional<FirebaseAuth> firebaseAuth, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.firebaseAuth = firebaseAuth.orElse(null);
        this.jwtService = jwtService;
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("INVALID_CREDENTIALS", "Invalid email or password"));

        if (user.getPassword() == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("INVALID_CREDENTIALS", "Invalid email or password");
        }

        return createAuthResponse(user);
    }

    @Transactional
    public AuthResponse loginWithFirebase(FirebaseLoginRequest request) {
        if (firebaseAuth == null) {
            throw new BadRequestException("FIREBASE_DISABLED", "Social login and OTP are currently disabled (missing configuration)");
        }
        try {
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(request.getIdToken());
            String email = decodedToken.getEmail();
            String phoneNumber = (String) decodedToken.getClaims().get("phone_number");
            String name = (String) decodedToken.getClaims().get("name");
            String googleId = decodedToken.getUid();

            User user;
            if (email != null && !email.isBlank()) {
                user = userRepository.findByEmail(email)
                        .orElseGet(() -> registerFirebaseUser(name, email, phoneNumber, googleId));
            } else if (phoneNumber != null && !phoneNumber.isBlank()) {
                user = userRepository.findByPhoneNumber(phoneNumber)
                        .orElseGet(() -> registerFirebaseUser(name, email, phoneNumber, googleId));
            } else {
                throw new BadRequestException("INVALID_TOKEN", "Token does not contain email or phone number");
            }

            if (user.getGoogleId() == null && googleId != null) {
                user.setGoogleId(googleId);
                userRepository.save(user);
            }

            return createAuthResponse(user);

        } catch (FirebaseAuthException e) {
            throw new BadRequestException("INVALID_TOKEN", "Firebase token verification failed: " + e.getMessage());
        }
    }

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if ((request.getEmail() == null || request.getEmail().isBlank()) &&
            (request.getPhoneNumber() == null || request.getPhoneNumber().isBlank())) {
            throw new BadRequestException("INVALID_INPUT", "Either email or phone number must be provided");
        }

        if (request.getEmail() != null && !request.getEmail().isBlank() && 
            userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("DUPLICATE_EMAIL", "Email is already registered");
        }

        if (request.getPhoneNumber() != null && !request.getPhoneNumber().isBlank() && 
            userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new BadRequestException("DUPLICATE_PHONE", "Phone number is already registered");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword() != null ? passwordEncoder.encode(request.getPassword()) : null)
                .role(Role.RESIDENT)
                .isActive(true)
                .build();

        User savedUser = userRepository.save(user);
        return createAuthResponse(savedUser);
    }

    private AuthResponse createAuthResponse(User user) {
        String subject = user.getEmail() != null ? user.getEmail() : user.getPhoneNumber();
        String token = jwtService.generateToken(subject, user.getRole().name());
        String refreshToken = jwtService.generateRefreshToken(subject);
        
        return new AuthResponse(token, refreshToken, mapToUserResponse(user));
    }

    private User registerFirebaseUser(String name, String email, String phoneNumber, String googleId) {
        User user = User.builder()
                .fullName(name != null ? name : "User " + (email != null ? email : phoneNumber))
                .email(email)
                .phoneNumber(phoneNumber)
                .googleId(googleId)
                .role(Role.RESIDENT)
                .isActive(true)
                .build();
        return userRepository.save(user);
    }

    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRole() != null ? user.getRole().name() : null
        );
    }
}
