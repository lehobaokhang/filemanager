package com.miroslav.filemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.miroslav.filemanager.service.EmailSenderService;

@Controller
public class LoginController {
	@Autowired
	private EmailSenderService emailSenderService;
	
	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}
}
