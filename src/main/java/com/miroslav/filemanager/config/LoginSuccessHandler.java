package com.miroslav.filemanager.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.miroslav.filemanager.entity.Token;
import com.miroslav.filemanager.service.EmailSenderService;
import com.miroslav.filemanager.service.TokenService;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	@Autowired
	private EmailSenderService emailSenderService;
	
	@Autowired
	private TokenService tokenService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		String redirectURL = request.getContextPath();
		
		if (userDetails.getUser().getStatus().equals("VERIFIED")) {
            redirectURL = "/";
        } else {
        	Token theToken = tokenService.save(userDetails.getUser().getId(), "verify-mail");
        	
        	String subject = "Verify Your Email";
			
			emailSenderService.sendVerifyMail(userDetails.getUser().getEmail(), subject, theToken.getToken());
        	
        	redirectURL = "verify-account";
        }
		
		response.sendRedirect(redirectURL);
	}
	
}
