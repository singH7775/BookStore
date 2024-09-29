package com.libraryStore.libraryStore.dto;

import com.libraryStore.libraryStore.models.Member;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	
	@Size(min = 10, message = "Username must be atleast 10 characters")
	private String username;
	
	@Size(min = 10, message = "Password must be atleast 10 characters")
	private String password;

	public MemberDTO(Member member) {
		super();
		this.username = member.getUsername();
		this.password = member.getPassword();
	}
	
	public Member memberToEntity() {
		Member member = new Member();	
		member.setUsername(this.getUsername());
		member.setPassword(this.getPassword());	
		return member;
	}
	
}
