package com.example.cloud_storage.controllers;

import com.example.cloud_storage.dtos.JwtRequest;
import com.example.cloud_storage.service.AuthService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static com.example.cloud_storage.configs.JwtRequestFilter.invalidTokens;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("auth-token") @NotNull String authToken) {

        System.out.println("invalidTokens:" + invalidTokens.toString());
        System.out.println("logout mapping started");

        invalidTokens.add(authToken);

        System.out.println("invalidTokens:" + invalidTokens.toString());
        return ResponseEntity.ok("successfully logged out");
    }

    /*
    TODO
    1. при логауте либо неверный метод ГЕТ, либо не выполняет метод из маппинга
    2. сделать оставшиеся методы
    */

}