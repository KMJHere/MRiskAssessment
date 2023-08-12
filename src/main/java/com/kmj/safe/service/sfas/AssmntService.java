package com.kmj.safe.service.sfas;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.kmj.safe.model.AssmntContent;
import com.kmj.safe.repository.sfas.AssmntMP;

public class AssmntService {
	@Autowired
	private AssmntMP assmntMP;
	
	public List<AssmntContent> selectAssmntLst() throws Exception {
		return assmntMP.selectAssmntLst();
	}
}
