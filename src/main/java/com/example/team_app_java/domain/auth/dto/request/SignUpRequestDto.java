package com.example.team_app_java.domain.auth.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SignUpRequestDto {

    private String email;
    private String password;
    private String name;
    private String profileImage;
    private String career;

    public SignUpRequestDto(String email, String password, String name, String profileImage, String career) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileImage = profileImage;
        this.career = career;
    }
}
