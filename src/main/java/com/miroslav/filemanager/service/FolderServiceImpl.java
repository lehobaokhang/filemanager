package com.miroslav.filemanager.service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miroslav.filemanager.entity.Folder;
import com.miroslav.filemanager.repository.FolderRepository;

@Service
public class FolderServiceImpl implements FolderService {
	@Autowired
	private FolderRepository folderRepository;
	
	@Override
	public void addFolder(int author, String folderName, String parentFolder) {
		Folder folder = new Folder();
		
		folder.setId(UUID.randomUUID().toString());
		folder.setFolderName(folderName);
		folder.setParentFolder(parentFolder);
		folder.setAuthor(author);
		Instant expired_at = Instant.now();
		folder.setCreated_at(Timestamp.from(expired_at));
		
		folderRepository.save(folder);
	}

	@Override
	public List<Folder> loadAllFolderByParentFolder(int author, String parentFolder) {
		return folderRepository.findByParentFolder(parentFolder);
	}
	
	
}
