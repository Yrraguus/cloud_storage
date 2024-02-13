package com.example.cloud_storage.exceptions;

public class DuplicateFileNameException extends RuntimeException {
    public DuplicateFileNameException(String msg) {
        super(msg);
    }
}
