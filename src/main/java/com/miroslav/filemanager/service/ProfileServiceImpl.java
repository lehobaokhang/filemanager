package com.miroslav.filemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miroslav.filemanager.entity.UserDetails;
import com.miroslav.filemanager.repository.UserDetailsRepository;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Override
	public UserDetails updateProfile(int profileId, UserDetails newProfile) {
		UserDetails oldProfile = userDetailsRepository.getById(profileId);
		
		oldProfile.setAddress(newProfile.getAddress());
		oldProfile.setDob(newProfile.getDob());
		oldProfile.setGender(newProfile.getGender());
		oldProfile.setPhone(newProfile.getPhone());
		
		return userDetailsRepository.save(oldProfile);
	}

	@Override
	public void changeAvatar(int profileId, String avatarPath) {
		UserDetails profile = userDetailsRepository.getById(profileId);
		profile.setAvatar(avatarPath);
		userDetailsRepository.save(profile);
	}
}
