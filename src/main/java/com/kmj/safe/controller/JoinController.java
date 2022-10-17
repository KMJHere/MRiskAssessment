package com.kmj.safe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kmj.safe.model.Member;
import com.kmj.safe.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class JoinController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/join")
	public void join() {
	}
	
	@PostMapping("/joinRegis")
	public String joinRegister(Member member, RedirectAttributes rttr) {
		log.info("Member?" + member);
		
		Integer userNo = memberService.createMember(member); 
		
		log.info("userNo: " + userNo);
		
		rttr.addFlashAttribute("USER_NO", userNo);
		
		return "redirect:/login";
	}
}
