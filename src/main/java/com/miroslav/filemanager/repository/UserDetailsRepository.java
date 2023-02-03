package com.miroslav.filemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miroslav.filemanager.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer>{

}
