package com.example.team_app_java.domain.blog.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class BlogFilter implements Filter {

    private static final List<String> NO_AUTH_REQUIRED = List.of(
            "/api/blogs",               // 전체 조회
            "/api/blogs/"               // 선택 조회 (예: /api/blogs/1)
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();

        // 인가 필요 없는 GET 요청은 필터 통과
        if (method.equals("GET") && (path.matches("^/api/blogs(/\\d+)?$"))) {
            chain.doFilter(request, response);
            return;
        }

        // 그 외의 요청은 JWT 검증
        String token = httpRequest.getHeader("Authorization");

        if (token == null || !isValidToken(token)) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isValidToken(String token) {
        // JWT 유효성 검사 로직
        return true;
    }
}
