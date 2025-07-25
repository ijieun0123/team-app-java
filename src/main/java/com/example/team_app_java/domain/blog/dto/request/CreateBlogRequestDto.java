package com.example.team_app_java.domain.blog.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreateBlogRequestDto {

    private String title;
    private String image;
    private String description;
    private String writerImage;
    private String writerName;
    private String career;

    public CreateBlogRequestDto(String title, String image, String description, String writerImage, String writerName, String career) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.writerImage = writerImage;
        this.writerName = writerName;
        this.career = career;
    }
}
