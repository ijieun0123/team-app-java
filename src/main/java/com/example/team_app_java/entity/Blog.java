package com.example.team_app_java.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;        
import lombok.Setter;  

@Entity
@Getter
@Setter
@Table(name = "blog")
public class Blog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "longtext")
    private String description;

    @Column(nullable = false)
    private String writerImage;

    @Column(nullable = false)
    private String writerName;

    @Column(nullable = false)
    private String career;

    public Blog() {
    }

    public Blog(String title, String image, String description, String writerImage, String writerName, String career) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.writerImage = writerImage;
        this.writerName = writerName;
        this.career = career;
    }
}
