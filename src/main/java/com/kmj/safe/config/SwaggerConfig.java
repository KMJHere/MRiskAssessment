package com.kmj.safe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
	/*
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.kmj.safe.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
				
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
					.title("Boot MRiskAssesment Swagger")
					.build();
	}
	*/
}
