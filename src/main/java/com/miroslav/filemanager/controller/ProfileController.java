package com.miroslav.filemanager.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.miroslav.filemanager.config.CustomUserDetails;
import com.miroslav.filemanager.entity.UserDetails;

@Controller
public class ProfileController {
	@GetMapping("/profile")
	public String showProfile(Model model, Authentication authentication) {
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		model.addAttribute("profile", userDetails.getUser().getUserDetails());
		return "profile";
	}
}
