package com.example.cloud_storage.dtos;

import lombok.Data;

@Data
public class JwtRequest {
    private String login;
    private String password;
}
