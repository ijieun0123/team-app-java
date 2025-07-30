package com.example.team_app_java.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SignUpRequestDto {

    @NotBlank(message = "REQUIRED_EMAIL")
    @Email(message = "INVALID_EMAIL_FORMAT")
    private String email;

    @NotBlank(message = "REQUIRED_PASSWORD")
    @Size(min = 8, max = 20, message = "INVALID_PASSWORD_LENGTH")
    private String password;

    @NotBlank(message = "REQUIRED_NAME")
    @Size(min = 2, max = 20, message = "INVALID_NAME_LENGTH")
    private String name;

    private String profileImage;

    @NotBlank(message = "REQUIRED_JOB")
    @Size(max = 100, message = "INVALID_CAREER_LENGTH")
    private String career;

    public SignUpRequestDto(String email, String password, String name, String profileImage, String career) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileImage = profileImage;
        this.career = career;
    }
}
