package com.example.team_app_java.domain.blog.filter;

import com.example.team_app_java.global.exception.ErrorResponse;
import com.example.team_app_java.global.exception.InvalidTokenException;
import com.example.team_app_java.global.exception.UnauthenticatedException;
import com.example.team_app_java.global.util.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class BlogFilter implements Filter {

    private final JwtTokenProvider jwtTokenProvider;

    public BlogFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String path = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();

        try {
            // 1. 인가 필요 없는 블로그 전체/선택 조회 (GET만 허용)
            if (method.equals("GET") && path.matches("^/api/blogs(/\\d+)?$")) {
                chain.doFilter(request, response);
                return;
            }

            // 2. Authorization 헤더 확인
            String bearerToken = httpRequest.getHeader("Authorization");
            if (bearerToken == null || bearerToken.isBlank()) {
                throw new UnauthenticatedException();
            }

            // "Bearer " 접두사 제거하고 공백 제거
            if (!bearerToken.startsWith("Bearer ")) {
                throw new InvalidTokenException();
            }

            String token = bearerToken.substring(7).trim();

            // 3. 토큰 유효성 검증
            if (!jwtTokenProvider.validateToken(token)) {
                throw new InvalidTokenException();
            }

            // 통과
            chain.doFilter(request, response);

        } catch (UnauthenticatedException | InvalidTokenException e) {
            // 4. 에러 응답 직접 작성
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json;charset=UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            var errorBody = new ErrorResponse("UNAUTHENTICATED", e.getMessage(), null);
            httpResponse.getWriter().write(mapper.writeValueAsString(errorBody));
        }
    }
}
