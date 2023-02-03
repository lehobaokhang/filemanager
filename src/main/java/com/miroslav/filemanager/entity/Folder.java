package com.miroslav.filemanager.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Folder {
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "folder_name")
	private String folderName;
	
	@Column(name = "parent_folder")
	private String parentFolder;
	
	@Column(name = "author")
	private int author;
	
	@Column(name = "created_at")
	private Timestamp created_at;

	public Folder() {
		super();
	}

	public Folder(String id, String folderName, String parentFolder, int author, Timestamp created_at) {
		super();
		this.id = id;
		this.folderName = folderName;
		this.parentFolder = parentFolder;
		this.author = author;
		this.created_at = created_at;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getParentFolder() {
		return parentFolder;
	}

	public void setParentFolder(String parentFolder) {
		this.parentFolder = parentFolder;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "Folder [id=" + id + ", folderName=" + folderName + ", parentFolder=" + parentFolder + ", author="
				+ author + ", created_at=" + created_at + "]";
	}	
}
