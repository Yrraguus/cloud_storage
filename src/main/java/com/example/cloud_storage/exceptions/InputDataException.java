package com.example.cloud_storage.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputDataException extends RuntimeException {
    public InputDataException(String msg) {
        super(msg);
        log.error(msg);
    }
}
