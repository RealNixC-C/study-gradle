package com.nixc.app.member;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 복합키 구현
 * 
 * Serializable 구현
 * equals, hashCode 오버라이딩
 * 기본생성자 필수
 * 클래스는 public
 * */

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class MemberRolePK implements Serializable {

	private String username;
	private Long roleNo;
	
}
