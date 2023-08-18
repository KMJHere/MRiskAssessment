package com.kmj.safe.service.sfas;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kmj.safe.model.AssmntContent;
import com.kmj.safe.repository.sfas.AssmntMP;

@Service
@Transactional
public class AssmntService {
	@Autowired
	private AssmntMP assmntMP;
	
	public List<AssmntContent> selectAssmntLst() throws Exception {
		return assmntMP.selectAssmntLst();
	}
	
	public List<AssmntContent> selectAssmntDtlLst() throws Exception {
		return assmntMP.selectAssmnDtltLst();
	}
}
