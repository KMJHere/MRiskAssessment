package com.kmj.safe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kmj.safe.model.Member;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootTest
public class MemberServiceTests {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MemberService memberService;
	
	@Test
	public void getMember() {
		String USER_ID = "kmj";
		
		Member member = memberService.getMember(USER_ID);
		
		logger.info(member.toString());
	}
}
