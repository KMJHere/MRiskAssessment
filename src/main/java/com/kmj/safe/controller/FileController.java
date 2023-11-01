package com.kmj.safe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kmj.safe.dto.FileFileDTO;
import com.kmj.safe.dto.FileResultDTO;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@Log4j2
public class FileController {	
	@Value("${com.kmj.upload.path}")
	private String uploadPath;
	
	@PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public List<FileResultDTO> upload(FileFileDTO uploadFileDTO, @PathVariable String fileGrpNo) {
		log.info(uploadFileDTO);
		
		if(uploadFileDTO.getFiles() != null) {
			
			final List<FileResultDTO> list = new ArrayList<>();
			
			uploadFileDTO.getFiles().forEach(MultipartFile -> {
				
				String originalName = MultipartFile.getOriginalFilename();
				String uuid = fileGrpNo;
				
				Path savePath = Paths.get(uploadPath, uuid+"_"+originalName);
				
				boolean image = false;
				
				try {
					MultipartFile.transferTo(savePath);
					
					// 이미지 파일일 경우..
					if(Files.probeContentType(savePath).startsWith("image")) {
						image = true;
						
						File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originalName);
						
						Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);						
					}
				} catch(IOException e) {
					e.printStackTrace();
				}
				
				list.add(FileResultDTO.builder()
							.uuid(uuid)
							.fileName(originalName)
							.img(image).build()
				);
				
				
			});
			
			return list;
		}
		
		return null;
	}
	
	@GetMapping("/view/{fileName}")
	public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName) {
		Resource resource = new FileSystemResource(uploadPath+File.separator + fileName);
		
		String resourceName = resource.getFilename();
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
			
		} catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok().headers(headers).body(resource);
	}
	
	public Map<String, Boolean> removeFile(@PathVariable String fileName) {
		Resource resource = new FileSystemResource(uploadPath+File.separator + fileName);
		
		Map<String, Boolean> resultMap = new HashMap<>();
		boolean removed = false;
		
		try {
			String contentType = Files.probeContentType(resource.getFile().toPath());
			removed = resource.getFile().delete();
			
			// 섬네일 존재 시
			if(contentType.startsWith("image")) {
				File thumbnailFile = new File(uploadPath+File.separator + "s_" + fileName);
				
				thumbnailFile.delete();
				
			}
			
		} catch(Exception e) {
			log.error(e.getMessage());
		}
		
		resultMap.put("result", removed);
		
		return resultMap;
	}
	
	
}
