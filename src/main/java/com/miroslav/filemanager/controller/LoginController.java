package com.miroslav.filemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miroslav.filemanager.service.EmailSenderService;
import com.miroslav.filemanager.service.TokenService;

@Controller
public class LoginController {	
	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}
}
