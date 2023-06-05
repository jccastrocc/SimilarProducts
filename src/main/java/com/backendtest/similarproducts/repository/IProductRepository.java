package com.backendtest.similarproducts.repository;

import java.util.List;

import com.backendtest.similarproducts.model.dto.Product;

import reactor.core.publisher.Mono;

public interface IProductRepository {

	Mono<Product> findById(String id);
	Mono<List<String>> getSimilarProducts(String id);
}
