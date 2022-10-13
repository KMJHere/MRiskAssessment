package com.kmj.safe.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kmj.safe.security.service.MraUserDetails;
import com.kmj.safe.security.service.MraUserDetailsService;

public class CustomAuthenticationManager implements AuthenticationManager {
	@Autowired
    private UserDetailsService userDetailsService;
	private final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	logger.info("userId: " + authentication.getName()); 
    	logger.info("userId: " + authentication.getCredentials().toString()); 
    	
    	String userId = authentication.getName();
    	String userPassword = authentication.getCredentials().toString(); // PW_ID
    	
    	
    	logger.info("userId: " + userId);
    	
        MraUserDetails userDetails = (MraUserDetails)userDetailsService.loadUserByUsername(userId);
        
        logger.info("Username: " + userDetails.getUsername());
        logger.info("encode pw: " + userDetails.getPassword());
        logger.info("getAuthorities: " + userDetails.getAuthorities());
        
        if(!bCrypt.matches(userPassword, userDetails.getPassword())) throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername()
                , userDetails.getPassword()
                , userDetails.getAuthorities());
    }
}
