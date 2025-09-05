package com.nixc.app.configs.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter{

	// 필터에 종속된 객체
	private AuthenticationManager authenticationManager;
	
	// 개발자가 직접 생성한 tokenManager
	private JwtTokenManager jwtTokenManager;
	
	public JwtLoginFilter(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenManager = jwtTokenManager;
		this.setFilterProcessesUrl("/api/member/login"); // 해당 Url에 요청이 들어오면 실행
	}

	// 로그인 처리
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// 1.  username, password 꺼냄
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		// 2. Jwt의 Authentication Request 인증 객체 만듬
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		// 3. 만든 객체로 인증 진행 
		// UsernamePasswordAuthenticationToken에서 UserDetailService에서 UserDetailService의 loadUserByUsername 호출
		// 패스워드가 일치하는지 판별하고 해당 Authentication객체를 SecurityContextHolder에 담아줌
		return authenticationManager.authenticate(authenticationToken);
	}
	
	// 로그인 성공 했을 때 실행 메서드
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// 로그인 성공시 해당 사용자 정보(Authentication)을 활용하여 Token을 생성
		System.out.println("로그인 시도");
		String accessToken = jwtTokenManager.createAccessToken(authResult);
		String refreshToken = jwtTokenManager.createRefreshToken(authResult);
		
		/*
		 *  개발시 포트번호가 다르기 때문에 쿠키가 저장되지 않는다.
		 *  Boot에 같이 빌드해서 배포하면 쿠키가 저장됨
		 * */
		response.setHeader("accessToken", accessToken);
	}

	// 로그인이 실패 했을 때 실행 메서드
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		System.out.println("로그인실패");
		System.out.println("========================================"+failed.getMessage());
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(failed.getMessage());
	}
	
	
}
