package com.backendtest.similarproducts.handler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.backendtest.similarproducts.model.dto.Product;
import com.backendtest.similarproducts.service.IProductService;

import reactor.core.publisher.Mono;


@Component
public class ProductHandler {
	private final IProductService productService;

	public ProductHandler(IProductService productService) {
		this.productService = productService;
	}

	public Mono<ServerResponse> getProduct(ServerRequest request) {
		String productId = request.pathVariable("productId");
		Mono<Product> productMono = productService.getProduct(productId);

		return productMono
				.flatMap(product -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(product))
				.switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
						.bodyValue("{\"error\": \"Product Not found\"}"))
				.onErrorResume(throwable -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.bodyValue("{\"error\": \"Internal Server Error\"}"));
	}

	public Mono<ServerResponse> getSimilarProducts(ServerRequest request) {
		String productId = request.pathVariable("productId");
		Mono<List<Product>> productMono = productService.getSimilarProducts(productId);
		
		return ServerResponse.ok().body(productMono,List.class);
		/*
		 * return productService.getSimilarProducts(productId).flatMap(ids ->
		 * ServerResponse.ok().bodyValue(ids))
		 * .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)
		 * .contentType(MediaType.APPLICATION_JSON) .bodyValue("Product Not found"));
		 */
	}
}
