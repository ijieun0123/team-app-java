package com.example.team_app_java.domain.blog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BlogCreateRequestDto {

    @NotBlank(message = "REQUIRED_TITLE")
    @Size(max = 100, message = "INVALID_TITLE_LENGTH")
    private String title;

    private String image;

    @NotBlank(message = "REQUIRED_CONTENT")
    private String description;

    public BlogCreateRequestDto(String title, String image, String description) {
        this.title = title;
        this.image = image;
        this.description = description;
    }
}
