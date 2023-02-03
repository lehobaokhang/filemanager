package com.miroslav.filemanager.service;

import java.util.List;

import com.miroslav.filemanager.entity.Folder;

public interface FolderService {
	public void addFolder(int author, String folderName);
	
	public List<Folder> loadAllFolder(int author);
}
