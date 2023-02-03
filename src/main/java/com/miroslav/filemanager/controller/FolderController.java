package com.miroslav.filemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miroslav.filemanager.config.CustomUserDetails;
import com.miroslav.filemanager.entity.Folder;
import com.miroslav.filemanager.service.FolderService;

@Controller
public class FolderController {
	@Autowired
	private FolderService folderService;
	
	@PostMapping("/add-folder")
	public String addFolder(@RequestParam("foldername") String folderName, @RequestParam("parentFolder") String parentFolder,
			Authentication authentication) {
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		folderService.addFolder(userDetails.getUser().getId(), folderName, parentFolder);
		
		String redirectURL = "redirect:" + ((parentFolder.equals("/")) ?
											parentFolder :
											"/folder/" + parentFolder);
		
		return redirectURL;
	}
	
	@GetMapping("/folder/{folderId}")
	public String loadFolderByFolderId(@PathVariable String folderId, Model model, Authentication authentication) {
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		model.addAttribute("parentFolder", folderId);
		
		List<Folder> allFolder = folderService.loadAllFolderByParentFolder(userDetails.getUser().getId(), folderId);
		
		model.addAttribute("folders", allFolder);
		
		return "index";
	}
}
