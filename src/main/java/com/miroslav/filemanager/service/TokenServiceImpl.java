package com.miroslav.filemanager.service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miroslav.filemanager.entity.Token;
import com.miroslav.filemanager.repository.TokenRepository;

@Service
public class TokenServiceImpl implements TokenService {
	@Autowired
	private TokenRepository tokenRepository;
	
	@Override
	public void save(Token token) {
		Instant expired_at = Instant.now().plus(Duration.ofMinutes(5));
		token.setExpired_at(Timestamp.from(expired_at));
		tokenRepository.save(token);
	}
}
