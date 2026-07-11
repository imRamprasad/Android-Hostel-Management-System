package com.Ram.backend.auth.controller;

import com.Ram.backend.auth.dto.LoginRequest;
import com.Ram.backend.auth.dto.LoginResponse;
import com.Ram.backend.auth.service.AuthService;
import com.Ram.backend.common.response.ApiResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
        when(authService.login(any())).thenReturn(new LoginResponse("Login API working for email: resident@example.com", "dummy-jwt-token-day-2"));

        LoginRequest request = new LoginRequest();
        request.setEmail("resident@example.com");
        request.setPassword("secret123");

        ResponseEntity<ApiResponse<LoginResponse>> responseEntity = authController.login(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().success());
        assertEquals("Login successful", responseEntity.getBody().message());
        assertEquals("Login API working for email: resident@example.com", responseEntity.getBody().data().getMessage());
        assertEquals("dummy-jwt-token-day-2", responseEntity.getBody().data().getToken());
    }

    @Test
    void loginWithBlankEmailStillUsesServiceResponse() {
        when(authService.login(any())).thenReturn(new LoginResponse("Login API working for email: ", "dummy-jwt-token-day-2"));

        LoginRequest request = new LoginRequest();
        request.setEmail("");
        request.setPassword("secret123");

        ResponseEntity<ApiResponse<LoginResponse>> responseEntity = authController.login(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertFalse(responseEntity.getBody().data().getMessage().isEmpty());
        assertEquals("dummy-jwt-token-day-2", responseEntity.getBody().data().getToken());
    }
}