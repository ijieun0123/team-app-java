package com.example.team_app_java.global.exception;

public class ForbiddenException extends HandledException {

    public ForbiddenException() {
        super(ErrorCode.FORBIDDEN);
    }

    public ForbiddenException(String message) {
        super(ErrorCode.FORBIDDEN,message);
    }
}
