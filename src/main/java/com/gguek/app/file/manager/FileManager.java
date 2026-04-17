package com.gguek.app.file.manager;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {

	@Value("${app.upload.base}")
	private String path;
	
	public String fileSave(String name, MultipartFile mf) throws Exception{
		//1. 어디에 저장?
		File file = new File(path, name);
		if(!file.exists()) file.mkdirs();
		
		//2. 파일명?
		String fileName = UUID.randomUUID().toString();
		
		//3. 확장자?
		fileName = fileName+"_"+mf.getOriginalFilename();
		
		//4. 저장
		file = new File(file, fileName);
		
		//a. MultipartFile의 transferTo 메서드
			//mf.transferTo(file);
		
		//b. FileCopyUtils의 copy 메서드
			FileCopyUtils.copy(mf.getBytes(), file);
			
			return fileName;
	}
}
