package com.example.team_app_java.global.exception;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super("해당 정보에 접근할 권한이 없습니다.");
    }

    public AccessDeniedException(String message) {
        super(message);
    }
}
