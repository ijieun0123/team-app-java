package com.example.team_app_java.domain.blog.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BlogCreateRequestDto {

    private String title;
    private String image;
    private String description;

    public BlogCreateRequestDto(String title, String image, String description) {
        this.title = title;
        this.image = image;
        this.description = description;
    }
}
