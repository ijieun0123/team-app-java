package com.example.team_app_java.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String email;
    private String password;
    private String name;
    private String profileImage;
    private String career;

    public UserUpdateRequestDto(String email, String password, String name, String profileImage, String career) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileImage = profileImage;
        this.career = career;
    }
}
