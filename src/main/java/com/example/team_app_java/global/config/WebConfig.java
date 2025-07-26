package com.example.team_app_java.global.config;

import com.example.team_app_java.domain.blog.filter.BlogFilter;
import com.example.team_app_java.global.interceptor.AuthInterceptor;
import com.example.team_app_java.global.resolver.AuthArgumentResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${cors.allowed-origins}")
    private String allowedOrigins;

    private final AuthInterceptor authInterceptor;
    private final AuthArgumentResolver authArgumentResolver;  // 추가

    public WebConfig(AuthInterceptor authInterceptor,
                     AuthArgumentResolver authArgumentResolver) {
        this.authInterceptor = authInterceptor;
        this.authArgumentResolver = authArgumentResolver;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173", "https://ijieun0123.github.io")
                .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/auth/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authArgumentResolver);
    }

    @Bean
    public FilterRegistrationBean<BlogFilter> blogFilter() {
        FilterRegistrationBean<BlogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new BlogFilter());
        registrationBean.addUrlPatterns("/api/blogs/*"); // 블로그 관련 API만 필터 적용
        return registrationBean;
    }
}
