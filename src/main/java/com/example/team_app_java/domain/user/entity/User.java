package com.example.team_app_java.domain.user.entity;

import com.example.team_app_java.domain.blog.entity.Blog;
import com.example.team_app_java.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String profileImage;

    @Column(nullable = false)
    private String career;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Blog> blogs;

    protected User() {
    }

    public User(Long id, String email, String password, String name, String profileImage, String career, List<Blog> blogs) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileImage = profileImage;
        this.career = career;
        this.blogs = blogs;
    }
}
