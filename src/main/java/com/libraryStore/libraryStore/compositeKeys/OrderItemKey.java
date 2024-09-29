package com.libraryStore.libraryStore.compositeKeys;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class OrderItemKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "member_id")
	Integer memberId;
	
	@Column(name = "product_id")
	Integer productId;
	
	public OrderItemKey(Integer memberId, Integer productId) {
		this.memberId = memberId;
		this.productId = productId;
	}
	
}
