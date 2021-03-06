package com.chandra.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
public class FinalProjectChandraApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectChandraApplication.class, args);
	}

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.chandra.bus.controller")).build()
				.securitySchemes(Arrays.asList(apiKey()));
	}

	private ApiKey apiKey() {
		return new ApiKey("apiKey", "Authorization", "header");
	}
}
