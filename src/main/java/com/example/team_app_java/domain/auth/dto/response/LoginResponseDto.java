package com.example.team_app_java.domain.auth.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {

    private final String accessToken;
    private final Long userId;
    private final String email;
    private final String name;

    public LoginResponseDto(String accessToken, Long userId, String email, String name) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.email = email;
        this.name = name;
    }
}
