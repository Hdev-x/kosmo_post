package com.gguek.app.board.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gguek.app.board.common.dto.BoardDTO;
import com.gguek.app.board.notice.service.NoticeService;
import com.gguek.app.pager.Pager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice/*")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("list")
	public String list(@ModelAttribute("pager") Pager pager, Model model) throws Exception{
		List<BoardDTO> ar = noticeService.list(pager);
		model.addAttribute("list", ar);
		return "board/list";
	}
	
	@GetMapping("create")
	public String create(BoardDTO boardDTO) throws Exception{
		return "board/create";
	}
	
	@PostMapping("create")
	public String create(BoardDTO boardDTO, @RequestParam("attach") MultipartFile [] attach) throws Exception{
		int result = noticeService.create(boardDTO, attach);
		return "redirect:./list";
	}
}

