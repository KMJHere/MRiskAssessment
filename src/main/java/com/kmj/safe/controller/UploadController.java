package com.kmj.safe.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kmj.safe.dto.UploadFileDTO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@Log4j2
public class UploadController {	
	@Value("${com.kmj.upload.path}")
	private String uploadPath;
	
	@PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String upload(UploadFileDTO uploadFileDTO) {
		log.info(uploadFileDTO);
		
		if(uploadFileDTO.getFiles() != null) {
			uploadFileDTO.getFiles().forEach(MultipartFile -> {
				String originalName = MultipartFile.getOriginalFilename();
				String uuid = UUID.randomUUID().toString();
				
				Path savePath = Paths.get(uploadPath, uuid+"_"+originalName);
				
				try {
					MultipartFile.transferTo(savePath);
					
					// 썸네일..
					if(Files.probeContentType(savePath).startsWith("image")) {
						File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originalName);
						
						Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);						
					}
				} catch(IOException e) {
					e.printStackTrace();
				}
				
				
			});
		}
		
		return null;
	}
	
	
}
