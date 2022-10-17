package com.kmj.safe.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	/*
	@PostMapping("/loginProc") 
	public String loginProc(HttpServletRequest request, HttpServletResponse response) {
		request.getParameter(null)
	}
	*/
}