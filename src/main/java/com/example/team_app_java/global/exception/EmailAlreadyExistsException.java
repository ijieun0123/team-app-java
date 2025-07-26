package com.example.team_app_java.global.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("이 이메일은 이미 사용 중입니다.");
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
