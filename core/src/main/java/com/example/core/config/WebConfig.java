package com.example.core.config;

import com.example.core.interceptor.Interceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/css/**", "/plugins/**", "/images/**", "/js/**","/pages/login.html",
                        "/pages/videoShow.html","/video/**","/pages/chat.html","/medium/*");
    }
}
