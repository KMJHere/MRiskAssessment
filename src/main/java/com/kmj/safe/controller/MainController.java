package com.kmj.safe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kmj.safe.service.MainService;

import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;

@Controller
@Log4j2
@RequestMapping("/main/")
public class MainController {
	@Autowired 
	MainService mainService;
	
	@GetMapping("/load")
	public String loadMain(Model model) {
		return "main";
	}
}
