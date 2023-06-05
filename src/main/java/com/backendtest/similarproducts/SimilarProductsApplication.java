package com.backendtest.similarproducts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@EnableCaching
@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Similar Product Service API",
		version = "1.0",
		description = "API REST for displaying products similar to the currently viewed product by customers"
))
public class SimilarProductsApplication {

	@Value("${similarproducts.existingapis.api.base-url}")
	private String existingApisUrl;

	public static void main(String[] args) {
		SpringApplication.run(SimilarProductsApplication.class, args);
	}

	
    @Bean
    WebClient webClient() {
		return WebClient.builder().baseUrl(existingApisUrl)
				.build();
	}

   
}
