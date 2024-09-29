package com.libraryStore.libraryStore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

	@NotEmpty(message = "Name of the product please")
	private String productName;
	
	@Min(0)
	private double price;
	
}
