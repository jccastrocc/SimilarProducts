package com.backendtest.similarproducts.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.backendtest.similarproducts.model.dto.Product;
import com.backendtest.similarproducts.repository.impl.ProductRepositoryImpl;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ProductServiceImplTest {
	@Mock
	private ProductRepositoryImpl productRepository;

	@InjectMocks
	private ProductServiceImpl productService;

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
	 public void getProduct() {
		when(productRepository.findById(productId1)).thenReturn(Mono.just(productTest1));

	    Mono<Product> result = productService.getProduct(productId1);

	    StepVerifier.create(result)
	            .expectNext(productTest1)
	            .verifyComplete();
	}

	@Test
	 public void getProductNotFound() {
		when(productRepository.findById(productId1)).thenReturn(Mono.empty());
		 Mono<Product> result = productService.getProduct(productId1);
		    StepVerifier.create(result)
		        .expectNextCount(0)
		        .verifyComplete();
	}

	@Test
	public void getSimilarProducts() {

		List<String> similarProductIds = Arrays.asList(productId2, productId3);
		Mono<List<String>> monoSimilarProduct = Mono.just(similarProductIds);

		when(productRepository.getSimilarProducts(productId1)).thenReturn(monoSimilarProduct);
		when(productRepository.findById(productId2)).thenReturn(Mono.just(productTest2));
		when(productRepository.findById(productId3)).thenReturn(Mono.just(productTest3));

		Mono<List<Product>> result = productService.getSimilarProducts(productId1);

		verify(productRepository, times(1)).getSimilarProducts(productId1);

		List<Product> expectedProducts = new ArrayList<Product>();
		expectedProducts.add(productTest2);
		expectedProducts.add(productTest3);
		assertEquals(expectedProducts, result.block());

	}

	@Test
	    void getSimilarProductsProducNotFound() {
	     

	        when(productRepository.getSimilarProducts(productId1))
	                .thenReturn(Mono.empty());

	    
	        Mono<List<Product>> result = productService.getSimilarProducts(productId1);

	        verify(productRepository, times(1)).getSimilarProducts(productId1);
	       

	        assertNull(result.block());
	    }

}
