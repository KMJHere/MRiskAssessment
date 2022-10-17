package com.kmj.safe.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("LoginInterceptor preHandler");
		
		HttpSession session = request.getSession();
		
		logger.info((String)session.getAttribute("login"));
		
		if(session.getAttribute("login") == null) {
			response.sendRedirect("/login");
			return false;
		}
		
		return true;
	}
}
