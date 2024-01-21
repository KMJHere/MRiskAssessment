package com.kmj.safe.config;

import com.kmj.safe.security.handler.UserLoginFailureHandler;
import com.kmj.safe.security.handler.UserLoginSuccessHandler;
import com.kmj.safe.security.service.MraUserDetailsService;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final DataSource dataSource;
	
	@Autowired
    MraUserDetailsService userDetailsService;
    
    @Bean
    public WebSecurityCustomizer configure() {
    	return (web) -> web.ignoring().antMatchers("/css/**", "/img/**", "/js/**", "/plugin/**", "/font/**", "/assets/**");
    	//return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(auth -> auth.antMatchers("/login/**", "/join/**").permitAll()
				.anyRequest().authenticated());
        
        // login
        http.formLogin(login ->	login.loginPage("/login")
        		.successHandler(successHandler())
        		.failureHandler(failureHandler()) 
				.loginProcessingUrl("/login_proc") //post mapping 주소, security 처리 
				.usernameParameter("id")
				.passwordParameter("pw").permitAll())
        		.userDetailsService(userDetailsService);
        
        // remember-me 기능 구현
        http.rememberMe()
        		.key("remember")
        		.tokenRepository(persistentTokenRepository())
        		.userDetailsService(userDetailsService)
        		.tokenValiditySeconds(60*60*24*30);
        
        // logout
        http.logout()
	        .logoutUrl("/logout")   // 로그아웃 처리 URL (= form action url)
	        //.logoutSuccessUrl("/login") // 로그아웃 성공 후 targetUrl, 
	        // logoutSuccessHandler 가 있다면 효과 없으므로 주석처리.
	         // 로그아웃 핸들러 추가
	        .logoutSuccessHandler((request, response, authentication) -> {
	            response.sendRedirect("/login");
	        }) // 로그아웃 성공 핸들러
	        .deleteCookies("remember-me"); // 로그아웃 후 삭제할 쿠키 지정
        

        http.csrf().disable(); // csrf 토큰 비활성화
        
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
    
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
    	JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
    	repo.setDataSource(dataSource);
    	return repo;
    	
    }
}
