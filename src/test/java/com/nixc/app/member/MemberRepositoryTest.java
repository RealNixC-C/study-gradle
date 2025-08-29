package com.nixc.app.member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	void test() {
		// Member값 저장
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("test_user2");
		memberVO.setPassword("pw");
		memberVO.setName("test_user2");
		memberVO.setEmail("test2@example.com");
		memberVO.setBirth(LocalDate.now());
		
		List<MemberRoleVO> list = new ArrayList<>();
		
		// RoleVO 생성
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleNo(2L);
		
		// MemberRoleVO 생성
		MemberRoleVO memberRoleVO = new MemberRoleVO();
		memberRoleVO.setMemberVO(memberVO);
		memberRoleVO.setRoleVO(roleVO);
		
		// list에 저장
		list.add(memberRoleVO);
		
		// memberVO에 list 저장
		memberVO.setMemberRoleVOs(list);
		
		memberRepository.save(memberVO);
		
	}

}
