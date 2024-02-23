package com.example.cloud_storage.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String msg) {
        super(msg);
        log.error(msg);
    }
}
