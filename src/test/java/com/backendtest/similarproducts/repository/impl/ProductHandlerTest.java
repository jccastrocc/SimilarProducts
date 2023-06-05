package com.backendtest.similarproducts.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.backendtest.similarproducts.handler.ProductHandler;
import com.backendtest.similarproducts.model.dto.Product;
import com.backendtest.similarproducts.service.impl.ProductServiceImpl;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ProductHandlerTest {
	@Mock
	private ProductServiceImpl productService;

	@InjectMocks
	private ProductHandler productHandler;

	private Product productTest1;
	private Product productTest2;
	private Product productTest3;

	private String productId1;
	private String productId2;
	private String productId3;
	
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		productId1 = "34";
		productId2 = "88";
		productId3 = "16";

		productTest1 = new Product();
		productTest1.setId(productId1);
		productTest1.setName("Product test 1");
		productTest1.setAvailability(false);
		productTest1.setPrice(3.4);

		productTest2 = new Product();
		productTest2.setId(productId2);
		productTest2.setName("Product test 2");
		productTest2.setAvailability(true);
		productTest2.setPrice(8.8);

		productTest3 = new Product();
		productTest3.setId(productId3);
		productTest3.setName("Product test 3");
		productTest3.setAvailability(false);
		productTest3.setPrice(1.6);
	}
	
	
	@Test
	void notFound() {
		Mono<ServerResponse> result = ServerResponse.notFound().build();
		StepVerifier.create(result)
				.expectNextMatches(response -> HttpStatus.NOT_FOUND.equals(response.statusCode()))
				.expectComplete()
				.verify();
	}
	
	@Test
	void contentType() {
		Mono<ServerResponse>
				result = ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).build();
		StepVerifier.create(result)
				.expectNextMatches(response -> MediaType.APPLICATION_JSON.equals(response.headers().getContentType()))
				.expectComplete()
				.verify();
	}
	

}
