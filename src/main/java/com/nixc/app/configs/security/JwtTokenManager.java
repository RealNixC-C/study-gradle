package com.nixc.app.configs.security;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.nixc.app.member.MemberDao;
import com.nixc.app.member.MemberVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenManager {
	
	// Token을 생성하거나, Token을 검증
	
	// 알고리즘을 통해 token을 생성
	// 노출 금지, 모든 서버가 같은 값을 가져야함
	@Value("${jwt.secretKey}")
	private String secretKey;
	
	@Value("${jwt.tokenValidTime}")
	private Long tokenValidTime;
	
	@Value("${jwt.issuer}")
	private String issuer;
	
	// javax.crypto.SecretKey클래스
	private SecretKey key;
	
	@Value("${jwt.refreshTokenValidTime}")
	private Long refreshTokenValidTime;
	
	// 검증한 토큰의 값으로 DB에서 정보를 조회후 다시 세션에 넣기 위함
	@Autowired
	private MemberDao memberDao;
	
	// 생성자에서 코드 작성 가능. 초기화용
	// @PostConstruct = 생성자 호출전에 실행
	@PostConstruct
	public void init () {
		// 암호화 인코딩
		String k = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
		key = Keys.hmacShaKeyFor(k.getBytes());
	}
	
	// Token 발급
	// 토큰을 발급하여 String으로 반환
	// 로그인성공시 (id와 password가 일치하여 성공한 경우) 토큰 발급
	// 성공했으니 세션에있는 유저의 정보를 담고있느 Authentication 매개변수로받음
	public String createToken(Authentication authentication, Long validTime) {
		// 추가한 Jwt(Json Web Token)라이브러리 사용
		return Jwts
				.builder()
				// 사용자 ID
				.subject(authentication.getName()) // subject : 사용자의 ID(username)
				// 권한 정보
				.claim("roles", authentication.getAuthorities().toString())
				.issuedAt(new Date()) // 토큰 생성 시간
				.expiration(new Date(System.currentTimeMillis() + validTime)) // 현재 시간 기준으로 3분 후
				.issuer(issuer) // 발급자
				.signWith(key) // 만들어둔 키
				.compact() // 전체를 String Type으로 변환
				;
	}
	
	public String createAccessToken(Authentication authentication) {
		return this.createToken(authentication, tokenValidTime);
	}
	
	public String createRefreshToken(Authentication authentication) {
		return this.createToken(authentication, refreshTokenValidTime);
	}
	
	// Token 검증
	public Authentication getAuthenticationByToken(String token) throws Exception {
		// 검증 - 여기서 실패시 Exception 발생
		Claims claims = Jwts
				.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				;
		
		// 어기까지 오면 검증 통과
		// claims에서 id를 꺼낸후 DB에서 조회
		MemberVO memberVO = new MemberVO();
		memberVO.setMemberId(claims.getSubject());
		memberVO = memberDao.login(memberVO);
		
		// MemberVO(UserDetail)를 Authentication으로 변경
		Authentication authentication = new UsernamePasswordAuthenticationToken(memberVO, null, memberVO.getAuthorities());
		
		return authentication; 
	}
	
}
