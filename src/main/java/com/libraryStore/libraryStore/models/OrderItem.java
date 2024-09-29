package com.libraryStore.libraryStore.models;

import com.libraryStore.libraryStore.compositeKeys.OrderItemKey;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

	@EmbeddedId
	OrderItemKey id;
	
	@ManyToOne
	@MapsId("memberId")
	@JoinColumn(name = "member_id")
	private Member member;
	
	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private Product product;
	
	private int quantity;

	public OrderItem(Member member, Product product, int quantity) {
        this.member = member;
        this.product = product;
        this.quantity = quantity;
        this.id = new OrderItemKey(member.getId(), product.getId());
    }
}
