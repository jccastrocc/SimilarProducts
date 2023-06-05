package com.backendtest.similarproducts.model.dto;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.backendtest.similarproducts.constants.NumericConstants;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {

	@NotEmpty
	@Size(min = NumericConstants.NUMBER_1, message = "The id must have a minimum length of {min} character.")
	private String id;

	@NotEmpty
	@Size(min = NumericConstants.NUMBER_1, message = "The name must have a minimum length of {min} character.")
	private String name;

	private double price;
	private boolean availability;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Product product = (Product) obj;
		return (Objects.equals(id, product.id) || (Objects.equals(name, product.name)
				&& Double.compare(product.price, price) == 0 && availability == product.availability));
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, price, availability);
	}
}
