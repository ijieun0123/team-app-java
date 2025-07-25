package com.example.team_app_java.domain.blog.service;

import com.example.team_app_java.domain.blog.dto.response.BlogResponseDto;
import com.example.team_app_java.domain.blog.entity.Blog;
import com.example.team_app_java.domain.blog.repository.BlogRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    // 블로그 글 생성
//    public BlogResponseDto save(String title, String image, String description) {
//
//        Blog blog = new Blog(title, image, description, user);
//
//        Blog savedBlog = blogRepository.save(blog);
//
//        return new BlogResponseDto(savedBlog.getId(), savedBlog.getTitle(), savedBlog.getDescription(), savedBlog.getImage(), savedBlog.getCreatedAt());
//    }

    // 블로그 글 전체 조회
    public List<BlogResponseDto> findAll() {
        List<BlogResponseDto> blogs = blogRepository.findAll()
                .stream()
                .map(BlogResponseDto::toDto)
                .collect(Collectors.toList());

        return blogs;
    }

    // 블로그 글 선택 조회
    public BlogResponseDto findById(Long id) {
        Blog findBlog = blogRepository.findByIdOrElseThrow(id);

        return new BlogResponseDto(findBlog.getId(), findBlog.getTitle(), findBlog.getDescription(), findBlog.getImage(), findBlog.getUser(), findBlog.getCreatedAt());
    }

    // 블로그 글 수정
    @Transactional
    public BlogResponseDto update(Long id, String title, String image, String description, String writerImage, String writerName, String career) {
        Blog findBlog = blogRepository.findByIdOrElseThrow(id);

        findBlog.setTitle(title);
        findBlog.setImage(image);
        findBlog.setDescription(description);

        return new BlogResponseDto(findBlog.getId(), findBlog.getTitle(), findBlog.getDescription(), findBlog.getImage(), findBlog.getUser(), findBlog.getCreatedAt());
    }

    // 블로그 글 삭제
    public void deleteById(Long id) {
        Blog findBlog = blogRepository.findByIdOrElseThrow(id);

        blogRepository.delete(findBlog);
    }
}
