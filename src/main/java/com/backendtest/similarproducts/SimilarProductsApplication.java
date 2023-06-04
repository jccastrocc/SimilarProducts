package com.backendtest.similarproducts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
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
