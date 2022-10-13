package com.kmj.safe.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;

@SuppressWarnings("serial")
public class MraUserDetails implements UserDetails {
	private String USER_ID;
	private String PASS_ID;
    
    @Builder
    public MraUserDetails(String username, String password, List<GrantedAuthority> authorities) {
    	this.USER_ID = username;
    	this.PASS_ID = password;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	List<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority("ROLE_USER"));
        return auth;
    }

    @Override
    public String getPassword() {
        return PASS_ID;
    }

    @Override
    public String getUsername() {
        return USER_ID;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
