package com.kmj.safe.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class UserInfo {
	private String SESSION_COMPANY_ID;
	
	private String SESSION_USER_NO;
	
	private String SESSION_USER_ID;
	
	private String SESSION_USER_NM;
	
	public Map<String, Object> getFixData() {
		Map<String, Object> mData = new HashMap<>();
		
		mData.put("SESSION_COMPANY_ID", this.getSESSION_COMPANY_ID());
		mData.put("SESSION_USER_NO", this.getSESSION_USER_NO());
		mData.put("SESSION_USER_ID", this.getSESSION_USER_ID());
		mData.put("SESSION_USER_NM", this.getSESSION_USER_NM());
		
		return mData;
	}
	
	public void setSessionData(HttpSession session) {
		this.setSESSION_COMPANY_ID((String)session.getAttribute("SESSION_COMPANY_ID"));
		this.setSESSION_USER_NO((String)session.getAttribute("SESSION_USER_NO"));
		this.setSESSION_USER_ID((String)session.getAttribute("SESSION_USER_ID"));
		this.setSESSION_USER_NM((String)session.getAttribute("SESSION_USER_NM"));
	}
	
	public void init() {
		this.setSESSION_COMPANY_ID(null);
		this.setSESSION_USER_NO(null);
		this.setSESSION_USER_ID(null);
		this.setSESSION_USER_NM(null);
	}
	
	public Map<String, Object> toMapping(Map<String, Object> amData) {
		amData.put("SESSION_COMPANY_ID", this.getSESSION_COMPANY_ID());
		amData.put("SESSION_USER_NO", this.getSESSION_USER_NO());
		amData.put("SESSION_USER_ID", this.getSESSION_USER_ID());
		amData.put("SESSION_USER_NM", this.getSESSION_USER_NM());
		
		return amData;
	}
	
}
