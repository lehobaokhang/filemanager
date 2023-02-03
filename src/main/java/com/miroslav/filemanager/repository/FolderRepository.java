package com.miroslav.filemanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.miroslav.filemanager.entity.Folder;

public interface FolderRepository extends JpaRepository<Folder, String>{
	@Query(value = "SELECT f FROM Folder f WHERE f.parentFolder = :parentFolder")
	List<Folder> findByParentFolder(@Param("parentFolder") String parentFolder);
}
