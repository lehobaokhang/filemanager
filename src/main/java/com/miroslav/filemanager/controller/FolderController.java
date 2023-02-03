package com.miroslav.filemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miroslav.filemanager.config.CustomUserDetails;
import com.miroslav.filemanager.service.FolderService;

@Controller
public class FolderController {
	@Autowired
	private FolderService folderService;
	
	@PostMapping("/add-folder")
	public String addFolder(@RequestParam("foldername") String folderName, Authentication authentication) {
		System.out.println(folderName);
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		folderService.addFolder(userDetails.getUser().getId(), folderName);
		
		return "redirect:/";
	}
	
	@GetMapping("/folder/{folderId}")
	public String loadFolderByFolderId(@PathVariable String folderId) {
		
		
		return "index";
	}
}
