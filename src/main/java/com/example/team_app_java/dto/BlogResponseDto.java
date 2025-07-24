package com.example.team_app_java.dto;

import com.example.team_app_java.entity.Blog;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BlogResponseDto {

    private final Long id;
    private final String title;
    private final String description;
    private final String image;
    private final String writerImage;
    private final String writerName;
    private final String career;
    private final LocalDateTime createdAt;

    public BlogResponseDto(Long id, String title, String description, String image, String writerImage, String writerName, String career, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.writerImage = writerImage;
        this.writerName = writerName;
        this.career = career;
        this.createdAt = createdAt;
    }

    public static BlogResponseDto toDto(Blog blog){
        return new BlogResponseDto(blog.getId(), blog.getTitle(), blog.getDescription(), blog.getImage(), blog.getWriterImage(), blog.getWriterName(), blog.getCareer(), blog.getCreatedAt());
    }
}
