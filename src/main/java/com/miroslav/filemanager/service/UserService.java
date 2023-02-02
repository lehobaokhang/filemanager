package com.miroslav.filemanager.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.miroslav.filemanager.entity.AuthUser;

public interface UserService extends UserDetailsService {
	public void register(AuthUser authUser);
	
	public boolean checkEmailExists(String email);
	
	public void verifyUser(int id);
	
	public void resetPassword(int userId, String newPwd);
}
