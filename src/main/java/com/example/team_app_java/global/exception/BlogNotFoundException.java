package com.example.team_app_java.global.exception;

public class BlogNotFoundException extends RuntimeException {
    public BlogNotFoundException() {
        super("존재하지 않는 블로그입니다.");
    }

    public BlogNotFoundException(String message) {
        super(message);
    }
}