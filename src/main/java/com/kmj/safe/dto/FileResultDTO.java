package com.kmj.safe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileResultDTO {	
	private String uuid;
	private String fileName;
	private boolean img;
	
	public String getLink() {
		// img일 경우 섬네일(s_)
		if(img) {
			return "s_" + uuid + "_" + fileName;
		} else {
			return uuid + "_" + fileName;
		}
	}	

}
