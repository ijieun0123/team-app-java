package com.example.team_app_java.global.interceptor;

import com.example.team_app_java.global.annotation.Auth;
import com.example.team_app_java.global.util.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthInterceptor(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        System.out.println("AuthInterceptor 생성됨");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // @Auth가 없는 경우 통과
        if (!(handler instanceof HandlerMethod)) return true;

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        boolean hasAuthAnnotation = handlerMethod.getMethodAnnotation(Auth.class) != null ||
                handlerMethod.getBeanType().getAnnotation(Auth.class) != null;

        if (!hasAuthAnnotation) return true;

        // 토큰 추출
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or missing JWT token");
            return false;
        }

        // 유저 ID를 요청 속성에 저장
        Long userId = jwtTokenProvider.getUserId(token);
        request.setAttribute("userId", userId);

        return true;
    }
}

