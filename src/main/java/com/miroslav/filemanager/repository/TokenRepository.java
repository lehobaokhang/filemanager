package com.miroslav.filemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.miroslav.filemanager.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Integer>{
	@Query(value = "select * from token where token= ?1", nativeQuery = true)
	Token findByTokenKey(String token);
	
	@Query(value = "SELECT u FROM Token u WHERE u.user =:user AND u.action=:action")
	Token findByUserId(@Param("user") int user, @Param("action") String action);
}
