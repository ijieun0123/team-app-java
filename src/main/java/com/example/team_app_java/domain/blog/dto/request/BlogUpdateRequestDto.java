package com.example.team_app_java.domain.blog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BlogUpdateRequestDto {

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 100, message = "제목은 100자 이하로 입력해주세요.")
    private String title;

    private String image;

    @NotBlank(message = "본문은 필수입니다.")
    private String description;

    public BlogUpdateRequestDto(String title, String image, String description) {
        this.title = title;
        this.image = image;
        this.description = description;
    }
}
