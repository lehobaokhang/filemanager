package com.miroslav.filemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miroslav.filemanager.config.CustomUserDetails;
import com.miroslav.filemanager.entity.AuthUser;
import com.miroslav.filemanager.entity.Token;
import com.miroslav.filemanager.service.EmailSenderService;
import com.miroslav.filemanager.service.TokenService;
import com.miroslav.filemanager.service.UserService;

@Controller
public class ResetPasswordController {
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	@GetMapping("/forgot-password")
	public String showForgotPasswordForm() {
		return "forgot-password";
	}
	
	@PostMapping("/forgot-pwd-processing")
	public String ForgotPasswordProcessing(@RequestParam("username") String username, RedirectAttributes redirectAttributes) {
		AuthUser authUser = ((CustomUserDetails) userService.loadUserByUsername(username)).getUser();
		
		if (authUser != null) {
			Token theToken = tokenService.save(authUser.getId(), "forgot-password");
		
			String subject = "Verify Your Email";
			emailSenderService.sendVerifyMail(authUser.getEmail(), subject, theToken.getToken());
			redirectAttributes.addFlashAttribute("message", "Please check your email");
			return "redirect:/login";
		} else {
			redirectAttributes.addFlashAttribute("message", "This email does not match with any accounts");
			return "redirect:/forgot-password";
		}
	}
	
	@GetMapping("/reset-password")
	public String showResetPasswordForm() {
		return "reset-password";
	}
	
	@PostMapping("/reset-pwd-processing")
	public String resetPasswordProcessing(@RequestParam("userId") int userId, @RequestParam("newPwd") String newPwd,
			RedirectAttributes redirectAttributes) {
		userService.resetPassword(userId, newPwd);
		
		redirectAttributes.addFlashAttribute("message", "Your password has been changed");
		
		return "redirect:/login";
	}
}
