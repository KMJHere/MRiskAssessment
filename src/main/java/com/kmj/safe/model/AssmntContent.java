package com.kmj.safe.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AssmntContent {
	private Integer COMPANY_ID; // 회사ID
	private String PROJ_CODE; // 현장코드
	private Integer R_COMPANY_ID; // 협력업체ID
	private Integer REGIS_SEQ; // 등록순번
	private Integer CRTUSERNO; // 최초생성자
	private Date CRTDATE; // 최초생성일
	private Integer MODUSERNO; // 최종수정자
	private Date MODDATE; // 최종수정자
	private String CHECK_TF; // 접수여부 [T:접수 F:미접수]
	private String MNG_DATE_FROM; // 관리기간_시작
	private String MNG_DATE_TO; // 관리기간_종료
	private String STAN_MONTH; // 기준월
	private String STAN_DEGREE; // 기준회차
	private String MAIN_CNSTRCT;  // 대공종
	private Integer JFILE_GRP_NO; // 파일그룹번호
	private String CNL_CMT; // 접수취소사유
	private String APPRV_CMT;  // 현장소장의견
}
