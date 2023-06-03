package com.backendtest.similarproducts.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.backendtest.similarproducts.constants.NumericConstants;

import lombok.Data;

@Data
public class Product {
	
	@NotEmpty
	@Size(min = NumericConstants.NUMBER_1, 
		message = "The id must have a minimum length of {min} character."
	)
	private String id;
	
	@NotEmpty
	@Size(min = NumericConstants.NUMBER_1, 
		message = "The name must have a minimum length of {min} character."
	)
	private String name;
	
	private double price;
	private boolean availability; 
}
