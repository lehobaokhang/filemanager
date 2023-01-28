package com.miroslav.filemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.miroslav.filemanager.entity.AuthUser;
import com.miroslav.filemanager.service.UserService;

@Controller
public class RegsiterController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String showFormRegister(Model model) {
		model.addAttribute("authUser", new AuthUser());
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute("authUser") AuthUser authUser) {
		if (userService.checkEmailExists(authUser.getEmail())) {
			userService.register(authUser);
		}
		return "redirect:/login";
	}
}
