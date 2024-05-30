package com.example.demo.Auth;

public class AuthResponse {
    private final boolean success;  // Use final for immutability
    private final String message;
    private final String token;

    public AuthResponse(String message, boolean success, String token) {
        this.message = message;
        this.success = success;
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }


}
