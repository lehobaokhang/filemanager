package com.miroslav.filemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.miroslav.filemanager.entity.AuthUser;

public interface AuthUserRepository extends JpaRepository<AuthUser, Integer>{
	@Query(value = "select * from auth_user where email = ?1", nativeQuery = true)
	AuthUser findByEmail(String email);
}
