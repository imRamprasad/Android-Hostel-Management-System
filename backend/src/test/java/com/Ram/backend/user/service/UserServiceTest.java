package com.Ram.backend.user.service;

import com.Ram.backend.common.exception.ResourceNotFoundException;
import com.Ram.backend.user.dto.CreateUserRequest;
import com.Ram.backend.user.dto.UserResponse;
import com.Ram.backend.user.entity.Role;
import com.Ram.backend.user.entity.User;
import com.Ram.backend.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void createUserShouldPersistAndReturnResponse() {
        CreateUserRequest request = new CreateUserRequest();
        request.setFullName("Aarav Sharma");
        request.setEmail("aarav@example.com");
        request.setPhoneNumber("9999999999");
        request.setPassword("secret123");

        User savedUser = User.builder()
                .id(10L)
                .fullName("Aarav Sharma")
                .email("aarav@example.com")
                .phoneNumber("9999999999")
                .password("encodedSecret123")
                .role(Role.RESIDENT)
                .build();

        when(passwordEncoder.encode(anyString())).thenReturn("encodedSecret123");
        when(userRepository.save(org.mockito.ArgumentMatchers.any(User.class))).thenReturn(savedUser);

        UserResponse response = userService.createUser(request);

        assertEquals(10L, response.getId());
        assertEquals("Aarav Sharma", response.getFullName());
        assertEquals("aarav@example.com", response.getEmail());
        assertEquals("9999999999", response.getPhoneNumber());
        assertEquals("RESIDENT", response.getRole());
        verify(userRepository).save(org.mockito.ArgumentMatchers.any(User.class));
    }

    @Test
    void getAllUsersShouldMapRepositoryResults() {
        User user = User.builder()
                .id(1L)
                .fullName("Resident One")
                .email("one@example.com")
                .phoneNumber("8888888888")
                .password("secret")
                .role(Role.RESIDENT)
                .build();

        when(userRepository.findAll()).thenReturn(List.of(user));

        List<UserResponse> responses = userService.getAllUsers();

        assertEquals(1, responses.size());
        assertEquals("Resident One", responses.get(0).getFullName());
    }

    @Test
    void getUserByIdShouldThrowWhenMissing() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(99L));

        assertEquals("USER_NOT_FOUND", exception.getErrorCode());
    }
}
