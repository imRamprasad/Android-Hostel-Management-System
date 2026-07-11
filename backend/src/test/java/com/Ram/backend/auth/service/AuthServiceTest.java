package com.Ram.backend.auth.service;

import com.Ram.backend.auth.dto.AuthResponse;
import com.Ram.backend.auth.dto.LoginRequest;
import com.Ram.backend.auth.security.JwtService;
import com.Ram.backend.user.entity.Role;
import com.Ram.backend.user.entity.User;
import com.Ram.backend.user.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class AuthServiceTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private FirebaseAuth firebaseAuth;
    private JwtService jwtService;
    private AuthService authService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        firebaseAuth = Mockito.mock(FirebaseAuth.class);
        jwtService = Mockito.mock(JwtService.class);
        authService = new AuthService(userRepository, passwordEncoder, Optional.of(firebaseAuth), jwtService);
    }

    @Test
    void loginShouldReturnAuthResponseForValidCredentials() {
        LoginRequest request = new LoginRequest();
        request.setEmail("resident@example.com");
        request.setPassword("secret123");

        User user = new User();
        user.setId(1L);
        user.setEmail("resident@example.com");
        user.setPassword("encodedPassword");
        user.setRole(Role.RESIDENT);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(jwtService.generateToken(anyString(), anyString())).thenReturn("test-token");
        when(jwtService.generateRefreshToken(anyString())).thenReturn("test-refresh-token");

        AuthResponse response = authService.login(request);

        assertEquals("test-token", response.getToken());
        assertEquals("test-refresh-token", response.getRefreshToken());
        assertEquals("resident@example.com", response.getUser().getEmail());
    }
}
