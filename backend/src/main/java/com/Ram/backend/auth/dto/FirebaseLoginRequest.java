package com.Ram.backend.auth.dto;

import jakarta.validation.constraints.NotBlank;

public class FirebaseLoginRequest {
    
    @NotBlank(message = "ID Token is required")
    private String idToken;

    public FirebaseLoginRequest() {
    }

    public FirebaseLoginRequest(String idToken) {
        this.idToken = idToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
