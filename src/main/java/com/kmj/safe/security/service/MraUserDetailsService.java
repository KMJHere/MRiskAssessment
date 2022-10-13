package com.kmj.safe.security.service; 

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import com.kmj.safe.model.Member;
import com.kmj.safe.service.MemberService;

@Service
@RequiredArgsConstructor
public class MraUserDetailsService implements UserDetailsService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private MemberService memberService;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	System.out.println("username?" + username);
    	
    	Member member = memberService.getMember(username);
    	
    	List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
    	/*
    	return User.builder()
    			.username(member.getUSER_ID())
    			.password(bCryptPasswordEncoder.encode(member.getPASS_ID()))
    			.roles("USER")
    			.build();
    	*/
    	return MraUserDetails.builder().username(member.getUSER_ID()).password(member.getPASS_ID()).authorities(roles).build();
    }
}
