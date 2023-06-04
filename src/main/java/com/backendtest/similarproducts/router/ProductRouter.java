package com.backendtest.similarproducts.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.backendtest.similarproducts.handler.ProductHandler;

@Configuration
public class ProductRouter {

	@Bean
	public RouterFunction<ServerResponse> productRoutes(ProductHandler productHandler) {
		return RouterFunctions.route(RequestPredicates.GET("/product/{productId}/similar"), productHandler::getSimilarProducts);}
}
