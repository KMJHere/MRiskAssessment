package com.kmj.safe.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileFileDTO {
	private List<MultipartFile> files;
}
