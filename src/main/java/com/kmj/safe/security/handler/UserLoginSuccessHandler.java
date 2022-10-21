package com.kmj.safe.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.kmj.safe.security.service.MraUserDetails;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

public class UserLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		
		MraUserDetails member = (MraUserDetails)authentication.getPrincipal();
		
		System.out.println("member?" + member.getUserno());
		System.out.println("member?" + member.getUsername());
		System.out.println("member?" + member.getCompanyId());
		
		HttpSession session = request.getSession();

    	session.setAttribute("login", "success");
    	session.setAttribute("COMPANY_ID", member.getCompanyId());
    	session.setAttribute("USER_NO", member.getUserno());
    	session.setAttribute("USER_ID", member.getUsername());
    	session.setAttribute("USER_NM", member.getUsername());
    	response.sendRedirect("/");
    }
}
