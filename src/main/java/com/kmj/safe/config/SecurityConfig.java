package com.kmj.safe.config;

import com.kmj.safe.security.filter.CustomAuthenticationProcessingFilter;
import com.kmj.safe.security.handler.CustomAuthenticationManager;
import com.kmj.safe.security.handler.UserLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {
    @Autowired
    UserDetailsService userDetailsService;
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // WebSecurityConfigurerAdapter > SecurityFilterChain 빈 등록 방식으로 변경
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
       
        http.authorizeRequests()
                .antMatchers("/user/**").hasRole("USER");


        // http.formLogin(); // 인가 or 인증에 문제 시 로그인 화면 반환
        http.csrf().disable(); // csrf 토큰 비활성화
        http.formLogin()
        				.loginPage("/login")
        				.defaultSuccessUrl("/user/main")	
        				.successHandler(successHandler())
						.loginProcessingUrl("/login_proc")
						.usernameParameter("id")
						.passwordParameter("pw").permitAll()
						.and()
						.addFilterBefore(customAuthenticationProcessingFilter(), 
		                        UsernamePasswordAuthenticationFilter.class); // 커스텀 로그인 추가
        
        
        
        /*
        
        (formLogin -> formLogin.loginPage("/login")
        		.successHandler(successHandler())
        		.loginProcessingUrl("/login_proc")
        		.usernameParameter("id")
        		.passwordParameter("pw")); // 커스텀 로그인 추가
        		*/
        
       
        http.logout(); // invalidatedHttpSession() deleteCookies() 쿠키나 세션을 무효화 시키는 설정 추가 가능

        // http.oauth2Login();
        
        /*
        // 소셜 로그인 처리 추가
        http.oauth2Login().successHandler(successHandler());

        // remember me 설정(초단위로 설정), 7일, 소셜 로그인은 x
        http.rememberMe().tokenValiditySeconds(60*60*7).userDetailsService(userDetailsService);

        // filter 동작 순서 바꿔보기
        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);

        http.addFilterBefore(apiLoginFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);
        */

        return http.build();
    }
    // [2022-06-18 김민정 수정 End]

    @Bean
    public UserLoginSuccessHandler successHandler() {
        return new UserLoginSuccessHandler();
    }

    //등록된 AuthenticationManager을 불러오기 위한 Bean
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    // 커스텀 인증 필터
    @Bean
    public CustomAuthenticationProcessingFilter customAuthenticationProcessingFilter() {
        CustomAuthenticationProcessingFilter filter = new CustomAuthenticationProcessingFilter("/login_proc");
        filter.setAuthenticationManager(customAuthenticationManager());
        //filter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler("/login"));
        filter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/"));
        return filter;
    }
    

    // 커스텀 인증 매니저 
    @Bean
    public CustomAuthenticationManager customAuthenticationManager() {
        return new CustomAuthenticationManager();
    }
}
