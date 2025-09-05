package com.nixc.app.member;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements UserDetailsService{

	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			System.out.println("memberService:" + username);
		try {
			return memberRepository.findById(username).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
