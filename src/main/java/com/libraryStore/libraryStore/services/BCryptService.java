package com.libraryStore.libraryStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptService {
	
	@Autowired
	BCryptPasswordEncoder bcrypt;
	
	public BCryptService(BCryptPasswordEncoder bcrypt) {
		super();
		this.bcrypt = bcrypt;
	}

	public String hashPassword(String plainPassword) {
		String hashPassword = bcrypt.encode(plainPassword);
		return hashPassword;
	}
	
}
