package com.hunter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//전역 크로스 오리진 설정
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/api/user/**") //어떤 요청 유알엘을 허용
                .allowedOrigins("http://localhost:3000") //어떤 클라이언트를 허용
                .allowedHeaders("*") //어떤 요청방식
                .allowedMethods("*") //어떤 헤더
                .allowCredentials(true) //쿠키전달 허용여부
                .maxAge(3600); //허용시간에 대한 캐싱설정
                ;

    }
}
