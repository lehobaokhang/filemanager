package com.miroslav.filemanager.service;

import com.miroslav.filemanager.entity.UserDetails;

public interface ProfileService {
	public UserDetails updateProfile(int profileId, UserDetails newProfile);
	
	public void changeAvatar(int profileId, String avatarPath);
}
