package com.nixc.app.member;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "member")
public class MemberVO {

	@Id
	private String username;
	
	private String password;
	private String name;
	private String email;
	
	@Temporal(TemporalType.DATE)
	private LocalDate birth;
	
	@OneToMany(mappedBy = "memberVO", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<MemberRoleVO> memberRoleVOs;
}
