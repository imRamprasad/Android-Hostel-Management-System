package com.Ram.backend.auth.controller;

import com.Ram.backend.auth.dto.AuthResponse;
import com.Ram.backend.auth.dto.LoginRequest;
import com.Ram.backend.auth.service.AuthService;
import com.Ram.backend.common.response.ApiResponse;
import com.Ram.backend.user.dto.UserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Test
    void loginShouldReturnWrappedSuccessResponse() {
        UserResponse userResponse = new UserResponse(1L, "John Doe", "resident@example.com", "1234567890", "RESIDENT");
        AuthResponse authResponse = new AuthResponse("token", "refreshToken", userResponse);
        when(authService.login(any())).thenReturn(authResponse);

        LoginRequest request = new LoginRequest();
        request.setEmail("resident@example.com");
        request.setPassword("secret123");

        ResponseEntity<ApiResponse<AuthResponse>> responseEntity = authController.login(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().success());
        assertEquals("Login successful", responseEntity.getBody().message());
        assertEquals("token", responseEntity.getBody().data().getToken());
    }
}
