package com.libraryStore.libraryStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.libraryStore.libraryStore.models.Member;
import com.libraryStore.libraryStore.repo.MemberRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class RootController {
	
	@Autowired
	MemberRepository memberRepository;
	
	@GetMapping("/")
	public String homePage() {
		return "home";
	}
	
	@GetMapping("/setMemberSession")
	public String setMemberSession(HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		Member findMember = memberRepository.findByUsername(username);
		if (findMember == null) {
			return "home";
		}
		session.setAttribute("memberId", findMember.getId());
		return "redirect:/store/storepage";
	}
	
}
