package com.example.cloud_storage.controllers;

import com.example.cloud_storage.dtos.JwtRequest;
import com.example.cloud_storage.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(@RequestHeader("auth-token") @NotNull String authToken) {
//        invalidTokens.add(authToken);
//        return ResponseEntity.ok("successfully logged out");
//    }
}