package com.backendtest.similarproducts.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.backendtest.similarproducts.model.dto.Product;
import com.backendtest.similarproducts.repository.IProductRepository;
import com.backendtest.similarproducts.service.IProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CacheConfig(cacheNames = {"productServiceCache"})
@Service
public class ProductServiceImpl implements IProductService{
	private IProductRepository productRepository;
	
	public ProductServiceImpl(IProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	@Cacheable()
	public Mono<Product> getProduct(String productId){
		return productRepository.findById(productId);
	}
	
	@Override
	@Cacheable()
	public Mono<List<Product>> getSimilarProducts(String productId){
		return productRepository.getSimilarProducts(productId)
				.flatMapMany(Flux::fromIterable)
	            .flatMap(this::getProduct) 
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
	
}
