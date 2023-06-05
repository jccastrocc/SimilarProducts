package com.backendtest.similarproducts.router;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.backendtest.similarproducts.handler.ProductHandler;
import com.backendtest.similarproducts.model.dto.Product;



@Configuration
public class ProductRouter {

	
	@Bean
	@RouterOperations(
			{
				 @RouterOperation(
                         path = "/product/{productId}/similar",
                         produces = {
                                 MediaType.APPLICATION_JSON_VALUE
                         },
                         method = RequestMethod.GET,
                         beanClass = ProductHandler.class,
                         beanMethod = "getSimilarProducts",
                         operation = @Operation(
                                 operationId = "getSimilarProducts",
                                 description = "List of similar products to a given one ordered by similarity",
                                 responses = {
                                        @ApiResponse(
                                                responseCode = "200",
                                                description = "successful operation",
                                                content = @Content(schema = @Schema(
                                                        implementation = Product.class
                                                ))
                                        ),
                                        @ApiResponse(responseCode="404", description = "Product Not Found")
                                 },
                                 parameters = {
                                		 @Parameter(in = ParameterIn.PATH,name = "productId")
                                 }
                         )
                 )
			})
	public RouterFunction<ServerResponse> productRoutes(ProductHandler productHandler) {
		 return RouterFunctions.route()
	                .GET("/product/{productId}/similar", productHandler::getSimilarProducts)
	                .build();
		}
}
