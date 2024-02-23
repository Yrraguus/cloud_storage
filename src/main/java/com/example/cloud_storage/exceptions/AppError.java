package com.example.cloud_storage.exceptions;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Data
@Slf4j
public class AppError {
    private int status;
    private String message;

    public AppError(int status, String message) {
        this.status = status;
        this.message = message;
        log.error(message);
    }
}
