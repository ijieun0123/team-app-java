package com.example.team_app_java.domain.blog.controller;

import com.example.team_app_java.domain.blog.dto.request.BlogCreateRequestDto;
import com.example.team_app_java.domain.blog.dto.response.BlogResponseDto;
import com.example.team_app_java.domain.blog.dto.request.BlogUpdateRequestDto;
import com.example.team_app_java.domain.blog.dto.response.PageResponseDto;
import com.example.team_app_java.domain.blog.service.BlogService;
import com.example.team_app_java.domain.user.entity.User;
import com.example.team_app_java.global.annotation.Auth;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {

    @Autowired
    BlogService blogService;

    // 블로그 글 생성
    @PostMapping
    public ResponseEntity<BlogResponseDto> save(
            @Auth User user,
            @RequestBody @Valid BlogCreateRequestDto createBlogRequestDto
    ) {
        BlogResponseDto createBlogResponseDto = blogService.save(user, createBlogRequestDto);

        return new ResponseEntity<>(createBlogResponseDto, HttpStatus.CREATED);
    }

    // 블로그 글 전체 조회
    @GetMapping
    public ResponseEntity<PageResponseDto<BlogResponseDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        PageResponseDto<BlogResponseDto> blogs = blogService.findAll(page, size);

        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    // 블로그 글 선택 조회
    @GetMapping("/{id}")
    public ResponseEntity<BlogResponseDto> findById(@PathVariable Long id){
        BlogResponseDto blogResponseDto = blogService.findById(id);

        return new ResponseEntity<>(blogResponseDto, HttpStatus.OK);
    }

    // 블로그 글 수정
    @PatchMapping("/{id}")
    public ResponseEntity<BlogResponseDto> update(
            @Auth User user,
            @PathVariable Long id,
            @RequestBody @Valid BlogUpdateRequestDto blogUpdateRequestDto
    ){
        BlogResponseDto blogResponseDto = blogService.update(user, id, blogUpdateRequestDto);

        return new ResponseEntity<>(blogResponseDto, HttpStatus.OK);
    }

    // 블로그 글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Auth User user,
            @PathVariable Long id
    ){
        blogService.deleteById(user, id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
