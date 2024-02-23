package com.example.cloud_storage.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DuplicateFileNameException extends RuntimeException {
    public DuplicateFileNameException(String msg) {
        super(msg);
        log.error(msg);
    }
}
