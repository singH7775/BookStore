package com.libraryStore.libraryStore.models;

import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "product_name", nullable = false, unique = true, length = 255)
	private String productName;
	
	@Column(name = "product_price", nullable = false, length = 255)
	private double price;
	
	@OneToMany(mappedBy = "product")
	Set<OrderItem> orderItems;
	
}
