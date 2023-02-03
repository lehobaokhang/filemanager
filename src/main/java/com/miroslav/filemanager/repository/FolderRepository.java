package com.miroslav.filemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miroslav.filemanager.entity.Folder;

public interface FolderRepository extends JpaRepository<Folder, String>{

}
