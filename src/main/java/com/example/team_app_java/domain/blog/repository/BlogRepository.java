package com.example.team_app_java.domain.blog.repository;

import com.example.team_app_java.domain.blog.entity.Blog;
import com.example.team_app_java.global.exception.BlogNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    default Blog findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(BlogNotFoundException::new);
    }
}
