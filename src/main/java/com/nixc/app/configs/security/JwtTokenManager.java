package com.nixc.app.configs.security;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.nixc.app.member.MemberRepository;
import com.nixc.app.member.MemberVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenManager {

	// 1. Access 토큰 유효 시간
	@Value("${jwt.accessTokenValidTime}")
	private Long accessTokenValidTime;
	
	// 2. Refresh 토큰 유효 시간
	@Value("${jwt.refreshTokenValieTime}")
	private Long refreshTokenValidTime;
	
	// 3. issuer
	@Value("${jwt.issuer}")
	private String issuer;
	
	// 4. secretKey
	@Value("${jwt.secretKey}")
	private String secretKey;
	
	// 5. key
	private SecretKey key;
	
	@Autowired
	MemberRepository memberRepository;
	
	// A. Key 생성
	// 방법1. 생성자 호출시 생성
//	public JwtTokenManager() {
//		String mk = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
//		this.key = Keys.hmacShaKeyFor(mk.getBytes());
//	}
	
	// 방법2. 생성자 호출 후 
	@PostConstruct
	public void init() {
		String mk = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
		key = Keys.hmacShaKeyFor(mk.getBytes());
	}
	
	// B. Token 생성
	public String createToken(Authentication authentication, Long validTime) {
		return Jwts
				.builder()
				.subject(authentication.getName()) // 사용자 ID
				.claim("roles", authentication.getAuthorities().toString()) // 개발자가 넣은 정보
				.issuedAt(new Date()) // 토큰 생성시간
				.expiration(new Date(System.currentTimeMillis() + validTime)) // 만료 시간
				.issuer(issuer)
				.signWith(key)
				.compact()
				;
	}
	
	// C. Access Token 생성
	public String createAccessToken(Authentication authentication) {
		return this.createToken(authentication, accessTokenValidTime);
	}
	
	// D. refreshToken 생성
	
	public String createRefreshToken(Authentication authentication) {
		return this.createToken(authentication, refreshTokenValidTime);
	}
	
	// E. token 검증
	public Authentication verifyToken(String token) throws Exception {
		// 검증 시작
		// claims = 토큰 안에있는 payload 정보
		Claims claims = Jwts
						.parser() // parser() = 토큰 검증 시작 메서드
						.verifyWith(key)
						.build()
						.parseSignedClaims(token) // JWT 토큰 파싱 & 검증
						.getPayload() // Token에서 payload만 꺼냄
						;
		
		// DB에서 ID와 일치하는 정보 조회
		Optional<MemberVO> result = memberRepository.findById(claims.getSubject());
		// 결과물에서 MemberVO 꺼냄
		MemberVO memberVO = result.get();
		
		// 해당메서드의 반환값 Authentication을 반환
		return new UsernamePasswordAuthenticationToken(memberVO, null, memberVO.getAuthorities());
		
	}
	
	
	
	
	
	
	
	
	
	
}
