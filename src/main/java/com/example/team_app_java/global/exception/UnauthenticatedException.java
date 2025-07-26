package com.example.team_app_java.global.exception;

public class UnauthenticatedException extends RuntimeException {
    public UnauthenticatedException() {
        super("로그인이 필요합니다.");
    }

    public UnauthenticatedException(String message) {
        super(message);
    }
}
