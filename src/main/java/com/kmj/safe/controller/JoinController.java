package com.kmj.safe.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kmj.safe.model.Member;
import com.kmj.safe.service.MemberService;
import com.kmj.safe.util.DatatableUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class JoinController {
	private static Logger logger = LoggerFactory.getLogger(JoinController.class); 
	@Autowired
	private MemberService memberService;
	@Autowired
	private DatatableUtil datatableUtil;
	
	@GetMapping("/join")
	public void join() {
	}
	
	@GetMapping("/searchCompanyId")
	public Map<String, Object> searchCompanyId(@RequestParam("draw") String asDtDraw,
			@RequestParam("start") int anDtStart,
			@RequestParam("length") int anDtLength,
			@RequestParam("companyName") String asCompanyName) throws Exception {
		Map<String, Object> mRtnDat = new HashMap<>();
		
		try {
			// 회사 목록 서비스 ..
			mRtnDat = datatableUtil.convertData(null, asDtDraw);
		} catch(Exception e) {
			logger.error("JoinControllerError: " + e);
		}
		
		return mRtnDat;
		
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