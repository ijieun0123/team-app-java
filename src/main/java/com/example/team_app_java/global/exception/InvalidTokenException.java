package com.example.team_app_java.global.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("유효하지 않은 인증 정보입니다. 다시 로그인해주세요.");
    }

    public InvalidTokenException(String message) {
        super(message);
    }
}
