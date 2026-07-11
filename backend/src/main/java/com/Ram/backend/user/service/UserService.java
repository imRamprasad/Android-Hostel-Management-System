package com.Ram.backend.user.service;

import com.Ram.backend.common.exception.ResourceNotFoundException;
import com.Ram.backend.user.dto.CreateUserRequest;
import com.Ram.backend.user.dto.UserResponse;
import com.Ram.backend.user.entity.User;
import com.Ram.backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserRequest request) {
        // 1. Map Request DTO -> Entity
        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword()) // Note: Encryption will be added on security days!
                .build();

        // 2. Save to MySQL via Repository
        User savedUser = userRepository.save(user);

        // 3. Map Entity -> Response DTO
        return mapToResponse(savedUser);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("USER_NOT_FOUND", "User not found with id: " + id));
        return mapToResponse(user);
    }

    // Helper method to keep code DRY (Don't Repeat Yourself)
    private UserResponse mapToResponse(User user) {
        return new UserResponse(user.getId(), user.getFullName(), user.getEmail(), user.getPhoneNumber());
    }
}