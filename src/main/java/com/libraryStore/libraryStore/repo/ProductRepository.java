package com.libraryStore.libraryStore.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryStore.libraryStore.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	Optional<Product> findById(int id);	
}
