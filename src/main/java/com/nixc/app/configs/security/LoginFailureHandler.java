package com.nixc.app.configs.security;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		// 로그인이 실패하면 Security에서 Exception을 발생
		log.info("{}", exception);
		// InternalAuthenticationServiceException : 아이디가 틀린경우
		// BadCredentialsException				  : 비밀번호가 틀린경우
		// DisabledException					  : 유효하지 않은 사용자
		// AccountExpiredException				  : 사용자 계정의 유효 기간이 만료
		// LockedException						  : 사용자 계정이 잠김
		// CredentialsExpiredException			  : 자격 증명 유효 기간이 만료
		
		String message = "관리자에게 문의";
		if(exception instanceof BadCredentialsException) message = "비밀번호 틀림";
		if(exception instanceof DisabledException) message = "유효하지 않은 사용자";
		if(exception instanceof AccountExpiredException) message = "사용자 계정의 유효 기간이 만료";
		if(exception instanceof LockedException) message = "사용자 계정이 잠김";
		if(exception instanceof CredentialsExpiredException) message = "자격 증명 유효 기간이 만료";
		if(exception instanceof InternalAuthenticationServiceException) message = "ID가 틀림";
		if(exception instanceof AuthenticationCredentialsNotFoundException) message = "관리자에게 문의";
		
		message = URLEncoder.encode(message, "UTF-8");
		
		response.sendRedirect("./login?failMessage=" + message);
	}
	
}
