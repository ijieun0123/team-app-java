package com.example.team_app_java.domain.user.dto.response;

import com.example.team_app_java.domain.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDto {

    private final Long id;
    private final String email;
    private final String name;
    private final String profileImage;
    private final String career;
    private final LocalDateTime createdAt;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.profileImage = user.getProfileImage();
        this.career = user.getCareer();
        this.createdAt = user.getCreatedAt();
    }

//    public UserResponseDto(Long id, String email, String name, String profileImage, String career, LocalDateTime createdAt) {
//    }

//    public static UserResponseDto toDto(User user){
//        return new UserResponseDto(user.getId(), user.getEmail(), user.getName(), user.getProfileImage(), user.getCareer(), user.getCreatedAt());
//    }
}
