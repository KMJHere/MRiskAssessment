package com.kmj.safe.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommonMP {
	List<Map<String, Object>> selectCompanyLst(@Param("asCompanyName") String COMPANY_NAME);
}
