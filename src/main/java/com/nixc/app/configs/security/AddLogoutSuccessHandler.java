package com.nixc.app.configs.security;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class AddLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		// 컨트롤러에서는 선언된 @RestController 어노테이션으로 JSON을 반환하지만
		// Handler에서는 아니기 때문에 응답받을 수 있는 데이터를 보냄
		response.getWriter().print(false);
		
	}
}
