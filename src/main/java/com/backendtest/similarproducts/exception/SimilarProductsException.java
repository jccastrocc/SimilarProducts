package com.backendtest.similarproducts.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimilarProductsException extends RuntimeException {
  
	private static final long serialVersionUID = -8356005987008836772L;

	public SimilarProductsException(String message) {
		super(message);
		log.error(message);
    }

    public SimilarProductsException(String message, Throwable cause) {
        super(message, cause);
    }
}
