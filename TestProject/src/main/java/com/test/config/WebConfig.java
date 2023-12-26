package com.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements  WebMvcConfigurer{

	// 인터셉터
	/*
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/member/**")
        		.excludePathPatterns("/member/logout")
        		.excludePathPatterns("/member/memInfo");
        


        registry.addInterceptor(new BoardInterceptor())
                .addPathPatterns("/board/**");
    }
    */
	//크로스 도메인 설정
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("*")
		.allowedMethods("GET", "POST", "PUT", "DELETE")
        .allowedHeaders("Authorization", "Content-Type")
        .exposedHeaders("Custom-Header")
        .allowCredentials(true)
        .maxAge(3600);
	}
}

