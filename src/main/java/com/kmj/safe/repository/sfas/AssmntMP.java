package com.kmj.safe.repository.sfas;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kmj.safe.model.AssmntContent;
import com.kmj.safe.model.AssmntDtlContent;

@Mapper
public interface AssmntMP {
	List<AssmntContent> selectAssmntLst();
	
	List<AssmntDtlContent> selectAssmnDtltLst();
	
	int insertAssmntLst(AssmntContent assmntContent);
	
	int insertAssmntDtlLst(AssmntDtlContent assmntContent);
}
