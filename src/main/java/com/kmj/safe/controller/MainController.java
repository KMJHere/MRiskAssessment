package com.kmj.safe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kmj.safe.service.MainService;

import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;

@Controller
@Log4j2
public class MainController {
	@Autowired 
	MainService mainService;
	
	@RequestMapping("/")
	public String loadMain(Model model) {
		return "/user/main";
	}

}
