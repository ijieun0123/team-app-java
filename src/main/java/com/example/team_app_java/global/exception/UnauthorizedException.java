package com.example.team_app_java.global.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }

    public static UnauthorizedException forBlogUpdate() {
        return new UnauthorizedException("블로그 글을 수정할 권한이 없습니다.");
    }

    public static UnauthorizedException forBlogDelete() {
        return new UnauthorizedException("블로그 글을 삭제할 권한이 없습니다.");
    }

    public static UnauthorizedException forUserGet() {
        return new UnauthorizedException("유저 정보를 조회할 권한이 없습니다.");
    }
}
