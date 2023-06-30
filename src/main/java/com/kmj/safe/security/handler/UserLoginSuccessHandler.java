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
		
		HttpSession session = request.getSession();

    	session.setAttribute("login", "success");
    	session.setAttribute("SESSION_COMPANY_ID", String.valueOf(member.getCompanyId()));
    	session.setAttribute("SESSION_USER_NO", String.valueOf(member.getUserno()));
    	session.setAttribute("SESSION_USER_ID", member.getUsername());
    	session.setAttribute("SESSION_USER_NM", member.getUserNm());

    	response.sendRedirect("/");
    }
}
