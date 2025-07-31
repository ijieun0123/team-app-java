package com.example.team_app_java.domain.blog.dto.response;

import java.util.List;

public record PageResponseDto<T>(
        List<T> content,
        int currentPage,
        int totalPages,
        long totalElements
) {}
