package com.libraryStore.libraryStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryStore.libraryStore.models.Member;
import com.libraryStore.libraryStore.models.OrderItem;
import com.libraryStore.libraryStore.models.Product;
import com.libraryStore.libraryStore.repo.OrderItemRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderItemService {

	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Transactional
	public void addProductToMember(Member member, Product product) {
		OrderItem presentOrderItem = orderItemRepository.findByMemberAndProduct(member, product);
		if (presentOrderItem != null) {
			presentOrderItem.setQuantity(presentOrderItem.getQuantity() + 1);
			orderItemRepository.save(presentOrderItem);
		} else {
			OrderItem newOrderItem = new OrderItem(member, product, 1);
			orderItemRepository.save(newOrderItem);
		}
	}
	
	@Transactional
	public void removeOrderToMember(Member member, Product product) {
		OrderItem presentOrderItem = orderItemRepository.findByMemberAndProduct(member, product);
		if (presentOrderItem != null) {
			orderItemRepository.delete(presentOrderItem);
		} else {
			System.out.println("No such order found for member");
		}
	}
}
