package com.example.team_app_java.domain.blog.entity;

import com.example.team_app_java.domain.user.entity.User;
import com.example.team_app_java.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;  

@Entity
@Getter
@Setter
@Builder
@Table(name = "blogs")
public class Blog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "longtext")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Blog() {
    }

    public Blog(Long id, String image, String title, String description, User user) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.user = user;
    }
}
