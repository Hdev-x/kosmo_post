package com.gguek.app.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.gguek.app.file.dto.FileDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FileDownView extends AbstractView{
	
	@Value("${app.upload.base}")
	private String base;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		System.out.println("file down view");
//		Iterator<String> it = model.keySet().iterator();
//		while (it.hasNext()) {
//			System.out.println(it.next()); 
//		}
		FileDTO fileDTO = (FileDTO)model.get("fileDTO");
		String name = (String)model.get("name");
		
		File file = new File(base, name); 
		file = new File(file, fileDTO.getFileName());
		
		//한글 인코딩
		response.setCharacterEncoding("UTF-8");
		
		//총 파일의 크기
		response.setContentLengthLong(file.length());
		
		//다운로드시 한글 파일 인코딩
		String originName = fileDTO.getOriginName();
		originName = URLEncoder.encode(originName, "UTF-8");
		
		//header
		response.setHeader("Content-Disposition", "attachment;filename=\""+originName+"\"");
		response.setHeader("Content-Transfer-Encoding", "banary");
		
		//파일 읽어오는 준비
		FileInputStream fi = new FileInputStream(file);
		
		//Client로 내보내는 준비
		OutputStream os = response.getOutputStream();
		
		//전송
		FileCopyUtils.copy(fi, os);
		
		//자원 해제
		os.close();
		fi.close();
		
	}

}
