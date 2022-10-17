package com.kmj.safe.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

public class UserLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		System.out.println("username? " + request.getAttribute("username"));
    	session.setAttribute("login", "success");
    	response.sendRedirect("/");
    }
}
