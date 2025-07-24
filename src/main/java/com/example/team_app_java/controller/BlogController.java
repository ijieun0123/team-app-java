package com.example.team_app_java.controller;

import com.example.team_app_java.dto.CreateBlogRequestDto;
import com.example.team_app_java.dto.BlogResponseDto;
import com.example.team_app_java.dto.UpdateBlogRequestDto;
import com.example.team_app_java.service.BlogService;
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
    public ResponseEntity<BlogResponseDto> save(@RequestBody CreateBlogRequestDto createBlogRequestDto) {
        BlogResponseDto createBlogResponseDto = blogService.save(createBlogRequestDto.getTitle(), createBlogRequestDto.getImage(), createBlogRequestDto.getDescription(), createBlogRequestDto.getWriterImage(), createBlogRequestDto.getWriterName() , createBlogRequestDto.getCareer());

        return new ResponseEntity<>(createBlogResponseDto, HttpStatus.CREATED);
    }

    // 블로그 글 전체 조회
    @GetMapping
    public ResponseEntity<List<BlogResponseDto>> findAll(){
        List<BlogResponseDto> blogs = blogService.findAll();

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
            @PathVariable Long id,
            @RequestBody UpdateBlogRequestDto updateBlogRequestDto
    ){
        BlogResponseDto blogResponseDto = blogService.update(id, updateBlogRequestDto.getTitle(), updateBlogRequestDto.getImage(), updateBlogRequestDto.getDescription(), updateBlogRequestDto.getWriterImage(), updateBlogRequestDto.getWriterName(), updateBlogRequestDto.getCareer());

        return new ResponseEntity<>(blogResponseDto, HttpStatus.OK);
    }

    // 블로그 글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        blogService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
