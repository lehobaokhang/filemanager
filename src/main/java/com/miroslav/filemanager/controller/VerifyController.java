package com.miroslav.filemanager.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.miroslav.filemanager.config.CustomUserDetails;
import com.miroslav.filemanager.entity.Token;
import com.miroslav.filemanager.service.EmailSenderService;
import com.miroslav.filemanager.service.TokenService;

@Controller
public class VerifyController {
	@Autowired
	private EmailSenderService emailSenderService;
	
	@Autowired
	private TokenService tokenService;
	
	@GetMapping("/verify-account")
	public String showVerifyForm() {
		return "resend-form";
	}
	
	@GetMapping("/resend-verify-mail")
	public void resendVerifyMail(Authentication authentication, HttpServletResponse response) throws IOException {
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		if (userDetails == null) {
			response.sendRedirect("/login");
		} else if (userDetails.getUser().getStatus().equals("VERIFIED")) {
			response.sendRedirect("/");
		} else {
			Token theToken = tokenService.save(userDetails.getUser().getId(), "verify-mail");
			
			String subject = "Verify Your Email";
			
			emailSenderService.sendVerifyMail(userDetails.getUser().getEmail(), subject, theToken.getToken());
		}
	}
	
	@GetMapping("/verify")
	public String verifyProcessing(@PathVariable("token") String token) {
		Token theToken = tokenService.findByTokenKey(token);
		
		
		return "";
	}
}
