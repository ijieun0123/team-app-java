package com.example.team_app_java.domain.blog.dto.response;

import com.example.team_app_java.domain.blog.entity.Blog;
import com.example.team_app_java.domain.user.entity.User;
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
    private final String writerName;
    private final String writerImage;
    private final String career;
    private final LocalDateTime createdAt;

    public BlogResponseDto(Long id, String title, String description, String image, String writerName, String writerImage, String career, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.writerName = writerName;
        this.writerImage = writerImage;
        this.career = career;
        this.createdAt = createdAt;
    }

    public static BlogResponseDto toDto(Blog blog){
        return new BlogResponseDto(blog.getId(), blog.getTitle(), blog.getDescription(), blog.getImage(), blog.getUser().getName(), blog.getUser().getProfileImage(), blog.getUser().getCareer(), blog.getCreatedAt());
    }
}
