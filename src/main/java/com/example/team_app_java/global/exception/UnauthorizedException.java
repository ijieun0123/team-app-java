package com.example.team_app_java.global.exception;

public class UnauthorizedException extends HandledException {

  public UnauthorizedException() {
    super(ErrorCode.AUTHORIZATION);
  }
  public UnauthorizedException(String message) {
    super(ErrorCode.AUTHORIZATION, message);
  }
}
