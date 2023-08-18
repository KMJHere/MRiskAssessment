package com.kmj.safe.controller.sfas;

import java.util.List;

import com.kmj.safe.model.AssmntContent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kmj.safe.service.sfas.AssmntService;

@Controller
public class AssmntController {
	@Autowired 
	private AssmntService assmntService;

	@GetMapping("/sfas/AssmntList")
	public String selectAssmntLst(Model model) throws Exception {	
		
		List<AssmntContent> assmntContent = assmntService.selectAssmntLst();
        model.addAttribute("result", assmntContent);
        
		return "sfas/AssmntList";
	}
}
