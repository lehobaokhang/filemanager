package com.miroslav.filemanager.controller;

import java.nio.file.Path;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miroslav.filemanager.config.CustomUserDetails;
import com.miroslav.filemanager.entity.UserDetails;
import com.miroslav.filemanager.service.ProfileService;

@Controller
public class ProfileController {
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private ServletContext application;
	
	@GetMapping("/profile")
	public String showProfile(Model model, Authentication authentication) {
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		model.addAttribute("profile", userDetails.getUser().getUserDetails());
		return "profile";
	}
	
	@PostMapping("/update-profile")
	public String updateProfile(@ModelAttribute("profile") UserDetails profile, Authentication authentication,
			RedirectAttributes redirectAttributes) {
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		UserDetails newProfile = profileService.updateProfile(userDetails.getUser().getUserDetails().getId(), profile);
		userDetails.getUser().setUserDetails(newProfile);
		
		redirectAttributes.addFlashAttribute("message", "Your profile has been changed");		
		return "redirect:/profile";
	}
	
	@PostMapping("/change-avatar")
	public String changeAvatar(@RequestParam("avatar") MultipartFile avatar, Authentication authentication) {
		if (!avatar.isEmpty()) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			String folder = "/uploads/avatars";
			
			try {
				String fileName = avatar.getOriginalFilename();
		        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
				String filePath = folder + "/" + userDetails.getUser().getId() + "." + fileExtension;
				
				avatar.transferTo(Path.of("src/main/resources/static" + filePath));
				
				profileService.changeAvatar(userDetails.getUser().getUserDetails().getId(), filePath);
				
				userDetails.getUser().getUserDetails().setAvatar(filePath);
			} catch (Exception e) {
				
			}
		}
		return "redirect:/profile";
	}
}
