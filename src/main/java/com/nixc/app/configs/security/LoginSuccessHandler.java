package com.nixc.app.configs.security;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.nixc.app.controller.HomeController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	// 로그인이 성공 했을때 실행
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("로그인 성공");

		UserDetails userDetails =(UserDetails) authentication.getPrincipal();
		String memberId = userDetails.getUsername();
		System.out.println(memberId);
		
		// checkbox의 value가져옴
		String rememberId = request.getParameter("rememberId");
		
		if(rememberId != null && rememberId.equals("1")) {
			// 쿠키 생성
			Cookie cookieRememberId = new Cookie("rememberId", memberId);
			// 유지 시간 설정
			cookieRememberId.setMaxAge(60*60*24*7);
			// 경로 설정
			cookieRememberId.setPath("/");
			// UTF-8 인코딩
			response.addCookie(cookieRememberId);
		} else {
			// 아이디 기억이 체크해제 되어있을경우 (rememberid == null)
			// 쿠키를 배열로 가져옴
			Cookie[] cookies = request.getCookies();
			// 배열이 null이 아니라면 반복문 시작
			if(cookies != null) {
				for(Cookie cookie : cookies) {
					// rememberId라는 키값을 가진 쿠키를 찾아 소멸
					if("rememberId".equals(cookie.getName())) {
						// 쿠키 유지시간 0
						cookie.setMaxAge(0);
						// 생성했을 때와 동일한 path 설정 필수
						cookie.setPath("/");
						// 쿠키 삭제
						response.addCookie(cookie);
						break;
					}
				}
			}
		}
		// 응답에 쿠키 등록
		
		response.sendRedirect("/");
	}

}
