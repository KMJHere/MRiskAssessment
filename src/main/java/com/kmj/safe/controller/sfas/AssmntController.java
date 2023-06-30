package com.kmj.safe.controller.sfas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AssmntController {

	@GetMapping("/sfas/AssmntList")
	public String selectAssmntLst() {	
		return "sfas/AssmntList";
	}
}
