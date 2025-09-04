package com.nixc.app.configs.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter{

	private JwtTokenManager jwtTokenManager;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {
		super(authenticationManager);
		this.jwtTokenManager = jwtTokenManager;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// 1. 토큰을 꺼냄
		Cookie[] cookies = request.getCookies();
		String token = "";
		
		if(cookies != null && cookies.length != 0) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("accessToken")) {
					token = cookie.getValue();
					break;
				}
			}
		}
		
		System.out.println("Token : " + token);
		// 2. 토큰이 비어있지 않을때 검증
		if(token != null && token.length() != 0) {
			try {
				// 토큰 매니저 클래스에 생성한 토큰 검증 메서드를 사용해 검증 완료 후 세션에 추가 
				Authentication authentication = jwtTokenManager.getAuthenticationByToken(token);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			} catch (Exception e) {
				e.printStackTrace();
				
				// Security Exception 또는 Malformed Exception 또는 Signature Exception : 유효하지 않는 JWT서명
				// ExpiredJwtException		: 기간이 만료된 토큰 // 만료 예외일 경우 refresh token을 사용하는것도 고려
				// Unsupported Exception	: 지원되지 않는 토큰
				// IllegalArgumentException	: 잘못된 토큰
				
				if(e instanceof ExpiredJwtException) {
					for(Cookie cookie : cookies) {
						if(cookie.getName().equals("refreshToken")) {
							String newToken = cookie.getValue();
							try {
								Authentication authentication = jwtTokenManager.getAuthenticationByToken(newToken);
								SecurityContextHolder.getContext().setAuthentication(authentication);
								newToken = jwtTokenManager.createAccessToken(authentication);
								
								Cookie c = new Cookie("accessToken", newToken);
								c.setPath("/");
								c.setMaxAge(180);
								c.setHttpOnly(true);
								
								response.addCookie(c);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
				}
			}
		}
		
		chain.doFilter(request, response);
	}
	
}
