package com.kmj.safe.config;

import com.kmj.safe.security.handler.UserLoginFailureHandler;
import com.kmj.safe.security.handler.UserLoginSuccessHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    UserDetailsService userDetailsService;
    
    @Bean
    public WebSecurityCustomizer configure() {
    	return (web) -> web.ignoring().antMatchers("/css/**", "/img/**", "/js/**", "/plugin/**", "/font/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(auth -> auth.antMatchers("/login").permitAll()
				.anyRequest().authenticated());
                
        http.formLogin(login ->	login.loginPage("/login")
        		.successHandler(successHandler())
        		.failureHandler(failureHandler()) 
				.loginProcessingUrl("/login_proc") //post mapping 주소, security 처리 
				.usernameParameter("id")
				.passwordParameter("pw").permitAll())
        	.rememberMe()
        		.rememberMeParameter("remember-me")
        		.userDetailsService(userDetailsService);
        
        

        http.csrf().disable(); // csrf 토큰 비활성화
        http.logout(); // invalidatedHttpSession() deleteCookies() 쿠키나 세션을 무효화 시키는 설정 추가 가능

        return http.build();
    }

    @Bean
    public UserLoginSuccessHandler successHandler() {
    	logger.info("UserLoginSuccessHandler Start");
        return new UserLoginSuccessHandler();
    }
    
    @Bean
    public UserLoginFailureHandler failureHandler() {
    	logger.info("UserLoginFailureHandler Start");
        return new UserLoginFailureHandler();
    }
}
