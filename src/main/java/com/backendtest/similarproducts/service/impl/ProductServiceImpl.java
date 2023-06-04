package com.backendtest.similarproducts.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.backendtest.similarproducts.model.dto.Product;
import com.backendtest.similarproducts.repository.IProductRepository;
import com.backendtest.similarproducts.service.IProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements IProductService{
	private IProductRepository productRepository;
	
	public ProductServiceImpl(IProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public Mono<Product> getProduct(String productId){
		return productRepository.findById(productId);
	}
	
	@Override
	public Mono<List<Product>> getSimilarProducts(String productId){
		return productRepository.getSimilarProducts(productId)
				.flatMapMany(Flux::fromIterable)
	            .flatMap(this::findProductByIdOrNull) 
	            .filter(Objects::nonNull)
	            .distinct() 
	            .collectList()
	            .flatMap(products -> {
	                if (products.isEmpty()) {
	                    return Mono.empty();
	                } else {
	                    return Mono.just(products);
	                }
	            });
	}
	
	
	private Mono<Product> findProductByIdOrNull(String productId) {
	    return productRepository.findById(productId)
	            .onErrorResume(throwable -> Mono.empty());
	}
}
