package com.libraryStore.libraryStore.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.libraryStore.libraryStore.models.Member;
import com.libraryStore.libraryStore.repo.MemberRepository;

@Service
public class MemberSecurityDetails implements UserDetailsService {

	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByUsername(username);
		if (member == null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("Username does not exist");
		}
		
		return new org.springframework.security.core.userdetails.
				User(member.getUsername(), member.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))); 
	}
	
}
