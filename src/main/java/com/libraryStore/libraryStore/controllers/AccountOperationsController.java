package com.libraryStore.libraryStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.libraryStore.libraryStore.dto.MemberDTO;
import com.libraryStore.libraryStore.models.Member;
import com.libraryStore.libraryStore.repo.MemberRepository;
import com.libraryStore.libraryStore.services.BCryptService;

import jakarta.validation.Valid;

@Controller
public class AccountOperationsController {
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	BCryptService bCrypt;

	@GetMapping("/signup")
	public String signupPage(Model model) {
		model.addAttribute("member", new MemberDTO());
		return "signup";
	}
	
	@GetMapping("/signin")
	public String signinPage(@RequestParam(required = false) String error, Model model) {
		if (error != null) {
			model.addAttribute("error", "Incorrect Username or Password!");
		}
		return "signin";
	}
	
	@PostMapping("/createaccount")
	public String createAccount(@Valid @ModelAttribute("member") MemberDTO member, BindingResult bindingResult, @RequestParam String verifyPassword, Model model,
								RedirectAttributes redirectAtt) {
		if (bindingResult.hasErrors()) {
			return "signup";
		}
		
		if (!member.getPassword().equals(verifyPassword)) {
			model.addAttribute("error", "Password do not match");
			return "signup";
		}
		
		Member userExists = memberRepository.findByUsername(member.getUsername());
		if (userExists != null) {
			model.addAttribute("error", "User already exists");
			return "signup";
		}
		
		Member newMember = new Member();
		newMember.setUsername(member.getUsername());
		newMember.setPassword(bCrypt.hashPassword(member.getPassword()));
		memberRepository.save(newMember);
		
		redirectAtt.addFlashAttribute("success", "Created account successfully, please sign in!");
		return "redirect:/signin";
	}
	
}
