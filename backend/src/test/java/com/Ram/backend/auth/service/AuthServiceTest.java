package com.Ram.backend.auth.service;

import com.Ram.backend.auth.dto.LoginRequest;
import com.Ram.backend.auth.dto.LoginResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthServiceTest {

    private final AuthService authService = new AuthService();

    @Test
    void loginShouldReturnTokenForEmail() {
        LoginRequest request = new LoginRequest();
        request.setEmail("resident@example.com");
        request.setPassword("secret123");

        LoginResponse response = authService.login(request);

        assertEquals("Login API working for email: resident@example.com", response.getMessage());
        assertEquals("dummy-jwt-token-day-2", response.getToken());
    }
}