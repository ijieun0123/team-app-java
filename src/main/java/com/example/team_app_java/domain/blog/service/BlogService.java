package com.example.team_app_java.domain.blog.service;

import com.example.team_app_java.domain.blog.dto.request.BlogCreateRequestDto;
import com.example.team_app_java.domain.blog.dto.request.BlogUpdateRequestDto;
import com.example.team_app_java.domain.blog.dto.response.BlogResponseDto;
import com.example.team_app_java.domain.blog.dto.response.PageResponseDto;
import com.example.team_app_java.domain.blog.entity.Blog;
import com.example.team_app_java.domain.blog.repository.BlogRepository;
import com.example.team_app_java.domain.user.entity.User;
import com.example.team_app_java.global.exception.BlogNotFoundException;
import com.example.team_app_java.global.exception.UnauthorizedException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    // 블로그 글 생성
    public BlogResponseDto save(User user, BlogCreateRequestDto blogCreateRequestDto) {
        Blog blog = Blog.builder()
                .title(blogCreateRequestDto.getTitle())
                .image(blogCreateRequestDto.getImage())
                .description((blogCreateRequestDto.getDescription()))
                .user(user)
                .build();

        Blog savedBlog = blogRepository.save(blog);

        return BlogResponseDto.toDto(savedBlog);
    }

    // 블로그 글 전체 조회
    public PageResponseDto<BlogResponseDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Blog> blogPage = blogRepository.findAll(pageable);

        List<BlogResponseDto> content = blogPage.getContent().stream()
                .map(BlogResponseDto::toDto)
                .toList();

        return new PageResponseDto<>(content, blogPage.getNumber(), blogPage.getTotalPages(), blogPage.getTotalElements());
    }

    // 블로그 글 선택 조회
    public BlogResponseDto findById(Long id) {
        Blog findBlog = blogRepository.findByIdOrElseThrow(id);

        return BlogResponseDto.toDto(findBlog);
    }

    // 블로그 글 수정
    @Transactional
    public BlogResponseDto update(User user, Long id, BlogUpdateRequestDto blogUpdateRequestDto) {
        Blog findBlog = blogRepository.findByIdOrElseThrow(id);

        // 작성자가 아닐 경우 체크
        if (!findBlog.getUser().getId().equals(user.getId())) {
            throw UnauthorizedException.forBlogUpdate();
        }

        findBlog.setTitle(blogUpdateRequestDto.getTitle());
        findBlog.setImage(blogUpdateRequestDto.getImage());
        findBlog.setDescription(blogUpdateRequestDto.getDescription());

        return BlogResponseDto.toDto(findBlog);
    }

    // 블로그 글 삭제
    public void deleteById(User user, Long id) {
        Blog findBlog = blogRepository.findByIdOrElseThrow(id);

        // 작성자가 아닐 경우 체크
        if (!findBlog.getUser().getId().equals(user.getId())) {
            throw UnauthorizedException.forBlogDelete();
        }

        blogRepository.delete(findBlog);
    }
}
