package com.example.team_app_java.global.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("비밀번호가 일치하지 않습니다.");
    }

    public InvalidPasswordException(String message) {
        super(message);
    }
}
