package com.nixc.app.member;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "role")
public class RoleVO {

	@Id
	private Long roleNo;
	private String roleName;
	
	@OneToMany(mappedBy = "roleVO", cascade = CascadeType.ALL)
	private List<MemberRoleVO> memberRoleVOs;
}
