package com.miroslav.filemanager.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="token")
public class Token {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name="user")
	private int user;
	
	@Column(name="token")
	private String token;
	
	@Column(name="action")
	private String action;
	
	@Column(name="expired_at")
	private Timestamp expired_at;
	
	public Token() {
		
	}

	public Token(int user, String token, String action, Timestamp expired_at) {
		super();
		this.user = user;
		this.token = token;
		this.action = action;
		this.expired_at = expired_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Timestamp getExpired_at() {
		return expired_at;
	}

	public void setExpired_at(Timestamp expired_at) {
		this.expired_at = expired_at;
	}
}