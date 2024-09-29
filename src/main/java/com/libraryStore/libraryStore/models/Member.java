package com.libraryStore.libraryStore.models;

import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "members_details")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "username", nullable = false, unique = true, length = 255)
	private String username;
	
	@Column(name = "password", nullable = false, length = 255)
	private String password;
	
	@OneToMany(mappedBy = "member")
	Set<OrderItem> orderItems;
	
}
