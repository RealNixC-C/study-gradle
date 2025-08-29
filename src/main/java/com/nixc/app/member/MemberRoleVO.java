package com.nixc.app.member;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="member_role")
public class MemberRoleVO {

//	private String username;
//	private Long roleNo;
	
//	@EmbeddedId 
//	private MemberRolePK memberRolePK;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username", nullable = false, insertable = false, updatable = false)
	@Id
	private MemberVO memberVO;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleNo", nullable = false, insertable = false, updatable = false)
	@Id
	private RoleVO roleVO;
	
}
