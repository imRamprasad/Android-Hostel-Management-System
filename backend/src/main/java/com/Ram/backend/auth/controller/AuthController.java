package com.Ram.backend.auth.controller;

import com.Ram.backend.auth.dto.AuthResponse;
import com.Ram.backend.auth.dto.FirebaseLoginRequest;
import com.Ram.backend.auth.dto.LoginRequest;
import com.Ram.backend.auth.dto.RegisterRequest;
import com.Ram.backend.auth.service.AuthService;
import com.Ram.backend.common.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        AuthResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(ApiResponse.success("Login successful", response));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        AuthResponse response = authService.register(registerRequest);
        return ResponseEntity.ok(ApiResponse.success("Registration successful", response));
    }

    @PostMapping("/google")
    public ResponseEntity<ApiResponse<AuthResponse>> loginWithGoogle(@Valid @RequestBody FirebaseLoginRequest loginRequest) {
        AuthResponse response = authService.loginWithFirebase(loginRequest);
        return ResponseEntity.ok(ApiResponse.success("Google login successful", response));
    }

    @PostMapping("/otp")
    public ResponseEntity<ApiResponse<AuthResponse>> loginWithOtp(@Valid @RequestBody FirebaseLoginRequest loginRequest) {
        AuthResponse response = authService.loginWithFirebase(loginRequest);
        return ResponseEntity.ok(ApiResponse.success("OTP login successful", response));
    }
}
