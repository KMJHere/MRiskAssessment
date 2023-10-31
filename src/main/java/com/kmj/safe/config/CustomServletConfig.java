package com.kmj.safe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CustomServletConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Swagger UI 적용 > 정적 파일 경로 재정의
		registry.addResourceHandler("/js/**")
				.addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/font/**")
				.addResourceLocations("classpath:/static/font/");
		registry.addResourceHandler("/assets/**")
				.addResourceLocations("classpath:/static/assets/");
		registry.addResourceHandler("/css/**")
				.addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/img/**")
				.addResourceLocations("classpath:/static/img/");
		registry.addResourceHandler("/img/**")
				.addResourceLocations("classpath:/static/plugin/");
		
		
				
	}
}
