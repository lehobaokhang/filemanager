package com.miroslav.filemanager.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miroslav.filemanager.config.CustomUserDetails;
import com.miroslav.filemanager.entity.Token;
import com.miroslav.filemanager.service.EmailSenderService;
import com.miroslav.filemanager.service.TokenService;
import com.miroslav.filemanager.service.UserService;

@Controller
public class VerifyController {
	@Autowired
	private EmailSenderService emailSenderService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserService userService;
	
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
	public String verifyProcessing(@RequestParam("token") String token, Authentication authentication, Model model,
			RedirectAttributes redirectAttributes) {
		Token theToken = tokenService.findByTokenKey(token);
		
		model.addAttribute("isLogin", (authentication == null) ? false : true);
		
		if (theToken == null) {
			model.addAttribute("message", "This token is invalid");
			model.addAttribute("isVerify", false);
			return "verify-account";
		}
		
		if (theToken.getExpired_at() == null) {
			model.addAttribute("isVerify", false);
			model.addAttribute("message", "This token is exipred");
			return "verify-account";
		}
		
		userService.verifyUser(theToken.getUser());
		
		String returnURL = (theToken.getAction().equals("verify-mail")) ? "verify-account" : "redirect:/reset-password";
		if (theToken.getAction().equals("verify-mail")) {
			model.addAttribute("message", "Your account has been verify");
			model.addAttribute("isVerify", true);
		} else {
			redirectAttributes.addFlashAttribute("userId", theToken.getUser());
		}
		return returnURL;
	}
}
