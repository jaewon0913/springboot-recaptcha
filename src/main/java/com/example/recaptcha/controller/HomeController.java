package com.example.recaptcha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.recaptcha.model.Member;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String loginPage() {
		return "index";
	}

	@GetMapping("afterLogin")
	public String afterLogin(Member member, Model model) {
		
		model.addAttribute("member", member);
		
		return "afterLogin";
	}
}
