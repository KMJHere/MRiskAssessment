package com.kmj.safe.controller.sfas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kmj.safe.dto.FileDTO;

@Controller
public class AssmntImgController {
	@GetMapping("/sfas/AssmntImgLst")
	public String selectAssmntImgLst() throws Exception {
		return "/sfas/AssmntImg";  
	}

}
