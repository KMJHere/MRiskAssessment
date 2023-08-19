package com.kmj.safe.repository.sfas;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kmj.safe.model.AssmntContent;

@Mapper
public interface AssmntMP {
	List<AssmntContent> selectAssmntLst();
	
	List<AssmntContent> selectAssmnDtltLst();
	
	int insertAssmntLst(AssmntContent assmntContent);
	
	int insertAssmntDtlLst(AssmntContent assmntContent);
}
