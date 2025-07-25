package com.example.team_app_java.global.exception;

public class ProductImageUploadException extends RuntimeException {
    public ProductImageUploadException(String message, Throwable cause){
        super(message, cause);
    }
}
