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
    private final User user;
    private final LocalDateTime createdAt;

    public BlogResponseDto(Long id, String title, String description, String image, User user, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.user = user;
        this.createdAt = createdAt;
    }

    public static BlogResponseDto toDto(Blog blog){
        return new BlogResponseDto(blog.getId(), blog.getTitle(), blog.getDescription(), blog.getImage(), blog.getUser(), blog.getCreatedAt());
    }
}
