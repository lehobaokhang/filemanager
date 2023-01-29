package com.miroslav.filemanager.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miroslav.filemanager.entity.AuthUser;
import com.miroslav.filemanager.entity.Token;
import com.miroslav.filemanager.service.EmailSenderService;
import com.miroslav.filemanager.service.TokenService;
import com.miroslav.filemanager.service.UserService;

@Controller
public class RegsiterController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	@Autowired
	private TokenService tokenService;
	
	@GetMapping("/register")
	public String showFormRegister(Model model) {
		model.addAttribute("authUser", new AuthUser());
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute("authUser") AuthUser authUser, RedirectAttributes redirectAttributes) {
		if (userService.checkEmailExists(authUser.getEmail())) {
			userService.register(authUser);
			
			Token token = new Token();
			token.setToken(UUID.randomUUID().toString());
			token.setUser(authUser.getId());
			token.setAction("verify-email");
			tokenService.save(token);			
			
			String subject = "Verify Your Email";
			
			emailSenderService.sendVerifyMail(authUser.getEmail(), subject, token.getToken());
			
			redirectAttributes.addFlashAttribute("message", "Please check your email");
			return "redirect:/login";
		} else {
			redirectAttributes.addFlashAttribute("message", "This email is being used");
			return "redirect:/register";
		}
	}
}
