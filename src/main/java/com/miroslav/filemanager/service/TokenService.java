package com.miroslav.filemanager.service;

import com.miroslav.filemanager.entity.Token;

public interface TokenService {
	public Token save(int userId, String action);
	
	public Token findByTokenKey(String token);
	
	public Token findByUserId(int userId, String action);
}
