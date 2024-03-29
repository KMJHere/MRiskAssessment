package com.kmj.safe.controller.sfas;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kmj.safe.model.AssmntContent;
import com.kmj.safe.model.AssmntDtlContent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kmj.safe.service.sfas.AssmntService;

@Controller
public class AssmntController {
	@Autowired 
	private AssmntService assmntService;

	@GetMapping("/sfas/AssmntList")
	public String selectAssmntLst(Model model) throws Exception {	
		
		List<AssmntContent> assmntContent = assmntService.selectAssmntLst();
        model.addAttribute("AssmntLst", assmntContent);
        
        return "forward:/sfas/AssmntDtlList";
	}
	
	@GetMapping("/sfas/AssmntDtlList")
	public String selectAssmnDtltLst(HttpServletRequest request, Model model, @RequestParam(required = false, defaultValue = "") String REGIS_SEQ) throws Exception {	
		
		List<AssmntDtlContent> assmntContent = assmntService.selectAssmntDtlLst(REGIS_SEQ);
        model.addAttribute("AssmntDtlLst", assmntContent);
       
        return "/sfas/AssmntList";
        
        // 하위 메소드 작성 후 model > 객체 추가 필요
        
	}
}
