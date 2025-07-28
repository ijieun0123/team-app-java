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

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8~20자 사이여야 합니다.")
    private String password;

    @NotBlank(message = "이름은 필수입니다.")
    @Size(min = 2, max = 20, message = "이름은 2~20자 사이여야 합니다.")
    private String name;

    private String profileImage;

    @NotBlank(message = "직업은 필수입니다.")
    @Size(max = 100, message = "경력 정보는 최대 100자까지 가능합니다.")
    private String career;

    public SignUpRequestDto(String email, String password, String name, String profileImage, String career) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileImage = profileImage;
        this.career = career;
    }
}
