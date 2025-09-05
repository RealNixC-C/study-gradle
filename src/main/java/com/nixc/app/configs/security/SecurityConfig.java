package com.nixc.app.configs.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nixc.app.board.notice.NoticeFileRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final NoticeFileRepository noticeFileRepository;
	
	@Autowired
	private JwtTokenManager jwtTokenManager;
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;

	@Autowired
	AddLogoutSuccessHandler addLogoutSuccessHandler;

    SecurityConfig(NoticeFileRepository noticeFileRepository) {
        this.noticeFileRepository = noticeFileRepository;
    }
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.cors(cors -> cors.configurationSource(this.corsConfiguration()))
			.csrf(csrf -> csrf.disable())
			
			// 권한 설정
			.authorizeHttpRequests(auth -> {
				auth
//					.requestMatchers("/api/notice").authenticated()
					.requestMatchers("/api/notice/add").hasRole("admin")
					.anyRequest().permitAll();
				})
			
			// 2. Form Login
			.formLogin(formLogin -> formLogin.disable())
			
			// 3. Logout
			.logout(logout -> {
				logout
				.logoutUrl("/api/member/logout")
				.invalidateHttpSession(true)
				.deleteCookies("access_token", "refresh_token")
				.logoutSuccessHandler(addLogoutSuccessHandler);
			})
			
			// 4. Session
			// 세션을 응답 나갈때삭제
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			
			// 5. httpBasic
			.httpBasic(http -> http.disable())
			
			// 6. Token에 관련된 필터를 등록
			.addFilter(new JwtAuthenticationFilter(this.authenticationConfiguration.getAuthenticationManager(), jwtTokenManager))
			.addFilter(new JwtLoginFilter(this.authenticationConfiguration.getAuthenticationManager(), jwtTokenManager))
			;
			
		return httpSecurity.build();
	}
	
	CorsConfigurationSource corsConfiguration() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("*"));
		
		// * 를 사용 못함
//		configuration.setAllowCredentials(true); // 를 사용하면
//		configuration.setAllowedOrigins(List.of("http://localhost:*")); // 사용안됨
		
		// Method에서 * 사용 X
		configuration.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE", "PUT", "OPTIONS"));
		configuration.setAllowedHeaders(List.of("Authorization"));
		
		configuration.setExposedHeaders(List.of("accessToken", "accesstoken"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}
}
