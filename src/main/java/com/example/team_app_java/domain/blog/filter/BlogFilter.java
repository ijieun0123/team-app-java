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

        // CORS 헤더 무조건 추가 (모든 응답에 대해)
        String origin = httpRequest.getHeader("Origin");
        if (origin != null) {
            httpResponse.setHeader("Access-Control-Allow-Origin", origin);
            httpResponse.setHeader("Vary", "Origin"); // CDN 캐싱 방지
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        }

        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, DELETE, OPTIONS");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // preflight 요청은 바로 통과
        if (method.equalsIgnoreCase("OPTIONS")) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        try {
            // 인가 필요 없는 GET 요청은 인증 없이 통과
            if (method.equals("GET") && path.matches("^/api/blogs(/\\d+)?$")) {
                chain.doFilter(request, response); // 헤더는 위에서 이미 넣었음
                return;
            }

            // JWT 인증 처리
            String bearerToken = httpRequest.getHeader("Authorization");
            if (bearerToken == null || bearerToken.isBlank()) {
                throw new UnauthenticatedException();
            }

            if (!bearerToken.startsWith("Bearer ")) {
                throw new InvalidTokenException();
            }

            String token = bearerToken.substring(7).trim();

            if (!jwtTokenProvider.validateToken(token)) {
                throw new InvalidTokenException();
            }

            chain.doFilter(request, response);

        } catch (UnauthenticatedException | InvalidTokenException e) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json;charset=UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            var errorBody = new ErrorResponse("UNAUTHENTICATED", e.getMessage(), null);
            httpResponse.getWriter().write(mapper.writeValueAsString(errorBody));
        }
    }
}
