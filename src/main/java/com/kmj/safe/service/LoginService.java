package com.kmj.safe.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	public String getLoginUrl(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		String url = "login";
		
		if(session.getAttribute("login").equals("success")) {
			// 로그아웃 선택 페이지 이동..
			url = "/user/main";
		}
		
		return url;
	}
}
