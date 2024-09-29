package com.libraryStore.libraryStore.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryStore.libraryStore.compositeKeys.OrderItemKey;
import com.libraryStore.libraryStore.models.Member;
import com.libraryStore.libraryStore.models.OrderItem;
import com.libraryStore.libraryStore.models.Product;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemKey> {
	OrderItem findByMemberAndProduct(Member member, Product product);
}
