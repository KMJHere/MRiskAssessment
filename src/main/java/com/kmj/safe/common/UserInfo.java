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
	private String COMPANY_ID;
	
	private String USER_NO;
	
	private String USER_ID;
	
	private String USER_NM;
	
	public Map<String, Object> getFixData() {
		Map<String, Object> mData = new HashMap<>();
		
		mData.put("SESSION_COMPANY_ID", this.getCOMPANY_ID());
		mData.put("SESSION_USER_NO", this.getUSER_NO());
		mData.put("SESSION_USER_ID", this.getUSER_ID());
		mData.put("SESSION_USER_NM", this.getUSER_NM());
		
		return mData;
	}
	
	public void setSessionData(HttpSession session) {
		this.setCOMPANY_ID((String)session.getAttribute("COMPANY_ID"));
		this.setUSER_NO((String)session.getAttribute("USER_NO"));
		this.setUSER_ID((String)session.getAttribute("USER_ID"));
		this.setUSER_NM((String)session.getAttribute("USER_NM"));
	}
	
	public void init() {
		this.setCOMPANY_ID(null);
		this.setUSER_NO(null);
		this.setUSER_ID(null);
		this.setUSER_NM(null);
	}
	
	public Map<String, Object> toMapping(Map<String, Object> amData) {
		amData.put("SESSION_COMPANY_ID", this.getCOMPANY_ID());
		amData.put("SESSION_USER_NO", this.getUSER_NO());
		amData.put("SESSION_USER_ID", this.getUSER_ID());
		amData.put("SESSION_USER_NM", this.getUSER_NM());
		
		return amData;
	}
	
}
