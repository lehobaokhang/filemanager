package com.miroslav.filemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.miroslav.filemanager.config.CustomUserDetails;
import com.miroslav.filemanager.entity.AuthUser;
import com.miroslav.filemanager.repository.AuthUserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthUserRepository authUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AuthUser user = authUserRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(user);
	}

	@Override
	public void register(AuthUser authUser) {
		String encodePassword = passwordEncoder.encode(authUser.getPassword());
		authUser.setPassword(encodePassword);
		authUser.setRole("ROLE_USER");
		authUser.setStatus("NON_VERIFIED");
		authUser.setUserDetails(new com.miroslav.filemanager.entity.UserDetails());
		authUserRepository.save(authUser);
	}

	@Override
	public boolean checkEmailExists(String email) {
		return (authUserRepository.findByEmail(email) == null) ? true : false;
	}
}
