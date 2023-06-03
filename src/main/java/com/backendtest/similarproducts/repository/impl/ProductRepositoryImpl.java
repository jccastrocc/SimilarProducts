package com.backendtest.similarproducts.repository.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.backendtest.similarproducts.model.dto.Product;
import com.backendtest.similarproducts.repository.IProductRepository;

import reactor.core.publisher.Mono;

@Repository
public class ProductRepositoryImpl implements IProductRepository {

	@Autowired
	WebClient webClient;
	
	@Override
	public Mono<Product> findById(String productId) {
		
		Product product = new Product();
		product.setAvailability(true);
		product.setId(productId);
		product.setName("testName");
		product.setPrice(2.366);
		
		return Mono.just(product);
	
		/*E
		 * return webClient.get() .uri("/product/{productId}", productId) .retrieve()
		 * .bodyToMono(Product.class) .onErrorResume(throwable -> Mono.error(new
		 * RuntimeException("Error getting the product detail", throwable)));
		 */
	}

	@Override
	public Mono<List<String>> getSimilarProducts(String productId) {
		
		List<String> testList = Arrays.asList("String 1", "String 2", "String 3");
		 return Mono.just(testList);
		
		/*
		 * return webClient.get() .uri("/product/{productId}/similarids", productId)
		 * .retrieve() .bodyToMono(new ParameterizedTypeReference<List<String>>() {})
		 * .onErrorResume(throwable -> Mono.error(new
		 * RuntimeException("Error getting similar products", throwable)));
		 */	}

}
