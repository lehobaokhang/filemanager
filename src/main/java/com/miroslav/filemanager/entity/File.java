package com.miroslav.filemanager.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class File {
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "folder_name")
	private String fileName;
	
	@Column(name = "parent_folder")
	private int parentFolder;
	
	@Column(name = "author")
	private int author;
	
	@Column(name = "created_at")
	private Date created_at;

	public File() {
		super();
	}

	public File(int id, String fileName, int parentFolder, int author, Date created_at) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.parentFolder = parentFolder;
		this.author = author;
		this.created_at = created_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFolderName() {
		return fileName;
	}

	public void setFolderName(String fileName) {
		this.fileName = fileName;
	}

	public int getParentFolder() {
		return parentFolder;
	}

	public void setParentFolder(int parentFolder) {
		this.parentFolder = parentFolder;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "Folder [id=" + id + ", folderName=" + fileName + ", parentFolder=" + parentFolder + ", author="
				+ author + ", created_at=" + created_at + "]";
	}	
}
