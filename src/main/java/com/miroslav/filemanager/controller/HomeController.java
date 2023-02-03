package com.miroslav.filemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.miroslav.filemanager.config.CustomUserDetails;
import com.miroslav.filemanager.entity.Folder;
import com.miroslav.filemanager.service.FolderService;

@Controller
public class HomeController {
	@Autowired
	private FolderService folderService;
	
	@GetMapping("/")
	public String showIndex(Model model, Authentication authentication) {
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		List<Folder> allFolder = folderService.loadAllFolderByParentFolder(userDetails.getUser().getId(), "/");
		
		model.addAttribute("folders", allFolder);
		model.addAttribute("parentFolder", "/");
		
		return "index";
	}
}
