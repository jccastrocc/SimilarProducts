package com.backendtest.similarproducts.repository.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.backendtest.similarproducts.exception.SimilarProductsException;
import com.backendtest.similarproducts.model.dto.Product;
import com.backendtest.similarproducts.repository.IProductRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@CacheConfig(cacheNames = {"productRepositoryCache"})
@Repository
public class ProductRepositoryImpl implements IProductRepository {

	
	private final WebClient webClient;

	public ProductRepositoryImpl(WebClient webClient) {
		this.webClient = webClient;
	}

	
	@Override
	@Cacheable()
	public Mono<Product> findById(String productId) {
		return webClient.get().uri("/product/{productId}", productId).retrieve().bodyToMono(Product.class)
				.onErrorResume(WebClientResponseException.NotFound.class, ex -> Mono.empty()).onErrorResume(
						throwable -> Mono.error(new RuntimeException("Error getting the product detail", throwable)));
	}

	@Override
	@Cacheable()
	public Mono<List<String>> getSimilarProducts(String productId) {

		return webClient.get().uri("/product/{productId}/similarids", productId).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<String>>() {
				}).onErrorResume(WebClientResponseException.NotFound.class, ex -> {
					log.error("Similar products not found for ID: {}", productId);
					return Mono.empty();
				}).onErrorResume(throwable -> {
					String errorMessage = String.format("Error getting similar products for ID: %s - %s", productId,
							throwable.getMessage());
					return Mono.error(new SimilarProductsException(errorMessage));
				});
	}

}
