package com.kmj.safe.service.common;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kmj.safe.common.UserInfo;
import com.kmj.safe.dto.FileDTO;
import com.kmj.safe.dto.FileResultDTO;
import com.kmj.safe.repository.common.CommonFileMP; 

import net.coobird.thumbnailator.Thumbnailator;


@Service
public class CommonFileService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonFileService.class);
	
	@Value("${com.kmj.upload.path}")
	private String uploadPath;
	@Autowired
	private CommonFileMP commonFileMP;	
	@Autowired
	private UserInfo userInfo;

	public List<Map<String, Object>> selectFilList(String asJfileGrpNo) throws Exception {
		String sCfgFileUploadRootDir = "";
		
		List<Map<String, Object>> lResult = commonFileMP.selectFileLst(asJfileGrpNo);
		
		for(Map<String, Object> mFile : lResult) {						
			Path pFile = Paths.get(sCfgFileUploadRootDir, (String)mFile.get("FILE_PATH"));
			mFile.put("EXISTS_TF", Files.exists(pFile) ? "T" : "F");
		}
		
		return lResult;		
	}	
	
	
	public List<FileResultDTO> upload(FileDTO uploadFileDTO, @PathVariable String fileGrpNo) throws Exception {
	
		List<Map<String, Object>> lSaveLst = new ArrayList<>();
		
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
	
}
