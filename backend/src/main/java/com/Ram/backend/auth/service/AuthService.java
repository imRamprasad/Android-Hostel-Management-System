package com.Ram.backend.auth.service;

import com.Ram.backend.auth.dto.LoginRequest;
import com.Ram.backend.auth.dto.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public LoginResponse login(LoginRequest request) {
        // Business logic placeholder (Checking passwords/generating JWT will live here later)
        return new LoginResponse("Login API working for email: " + request.getEmail(), "dummy-jwt-token-day-2");
    }
}