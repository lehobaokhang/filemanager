package com.miroslav.filemanager.service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miroslav.filemanager.entity.Token;
import com.miroslav.filemanager.repository.TokenRepository;

@Service
public class TokenServiceImpl implements TokenService {
	@Autowired
	private TokenRepository tokenRepository;
	
	@Override
	public Token save(int userId, String action) {
		Token token = findByUserId(userId, action);
		
		if (token == null) {
			token = new Token();
		}
		
		token.setToken(UUID.randomUUID().toString());
		token.setUser(userId);
		token.setAction(action);
		
		Instant expired_at = Instant.now().plus(Duration.ofMinutes(5));
		token.setExpired_at(Timestamp.from(expired_at));
		tokenRepository.save(token);

		return token;
	}

	@Override
	public Token findByTokenKey(String token) {
		Token theToken = tokenRepository.findByTokenKey(token);
		
		if (theToken != null) {
			tokenRepository.deleteById(theToken.getId());
		} else {
			return null;
		}
		
		if (Instant.now().isAfter(theToken.getExpired_at().toInstant())) {
			theToken.setExpired_at(null);
		}
		return theToken;
	}

	@Override
	public Token findByUserId(int userId, String action) {
		return tokenRepository.findByUserId(userId, action);
	}
}
