package com.gguek.app.board.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gguek.app.board.common.dto.BoardDTO;
import com.gguek.app.board.notice.dto.NoticeDTO;
import com.gguek.app.board.notice.dto.NoticeFileDTO;
import com.gguek.app.board.notice.service.NoticeService;
import com.gguek.app.file.dto.FileDTO;
import com.gguek.app.pager.Pager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice/*")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@Value("${app.board.notice}")
	private String name;
	
	@ModelAttribute("name")
	public String getName() {
		return this.name;
	}
	
	@GetMapping("down")
	public String fileDown(NoticeFileDTO noticeFileDTO, Model model) throws Exception{
		log.info("넘어온 파일 번호: {}", noticeFileDTO.getFileNum());
		FileDTO fileDTO = noticeService.fileDetail(noticeFileDTO);
		if (fileDTO == null) {
	        log.error("DB에 파일 정보가 없습니다!");
	        return "redirect:/notice/list"; 
	    }
		model.addAttribute("fileDTO", fileDTO);
	    model.addAttribute("name", "notice"); // <--- 이 'name'이 View의 경로가 됨
	    return "fileDownView";
		
		
	}
	
	@GetMapping("list")
	public String list(@ModelAttribute("pager") Pager pager, Model model) throws Exception{
		List<BoardDTO> ar = noticeService.list(pager);
		model.addAttribute("list", ar);
		return "board/list";
	}
	
	@GetMapping("detail")
	public String detail(NoticeDTO noticeDTO, Model model) throws Exception {
		BoardDTO boardDTO = noticeService.detail(noticeDTO);
		if (boardDTO != null) {
			model.addAttribute("d", boardDTO);
			return "board/detail";
		}else {
			model.addAttribute("result", "존재하지 않는 글입니다.");
			model.addAttribute("url", "./");
			return "commons/result";
		}
		
	}
	
	@GetMapping("create")
	public String create(BoardDTO boardDTO) throws Exception{
		return "board/create";
	}
	
	@PostMapping("create")
	public String create(BoardDTO boardDTO, @RequestParam("attach") MultipartFile [] attach, Model model) throws Exception{
		int result = noticeService.create(boardDTO, attach);
		if (result>0) {
			model.addAttribute("result", "글이 정상적으로 등록되었습니다.");
			model.addAttribute("url", "./list");
		}
		return "commons/result";
	}
	
	@GetMapping("update")
	public String update(NoticeDTO noticeDTO, Model model) throws Exception{
		BoardDTO boardDTO = noticeService.detail(noticeDTO);
		model.addAttribute("d", boardDTO);
		return "board/update";
	}
	
	@PostMapping("update")
	public String update(NoticeDTO noticeDTO, @RequestParam("attach") MultipartFile [] attach) throws Exception{
		int result = noticeService.update(noticeDTO, attach);
		return "redirect:./list";
	}
	
	@PostMapping("delete")
	public String delete(NoticeDTO noticeDTO) throws Exception {
		int result = noticeService.delete(noticeDTO);
		return "redirect:./list";
	}
}

