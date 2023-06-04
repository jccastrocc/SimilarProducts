package com.backendtest.similarproducts.service;

import java.util.List;

import com.backendtest.similarproducts.model.dto.Product;

import reactor.core.publisher.Mono;

public interface IProductService {

	Mono<Product> getProduct(String id);

	Mono<List<Product>> getSimilarProducts(String productId);

}
