package com.gguek.app.board.qna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gguek.app.board.common.dto.BoardDTO;
import com.gguek.app.board.qna.service.QnaService;
import com.gguek.app.pager.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	@GetMapping("list")
	public String list(Pager pager, Model model) throws Exception {
		List<BoardDTO> ar = qnaService.list(pager);
		model.addAttribute("list", ar);
		return "board/list";
	}
	
	@GetMapping("create") 
	public String create() throws Exception{
		return "board/create";
	}
	
	@PostMapping("create")
	public String create(BoardDTO boardDTO, @RequestParam("attach") MultipartFile [] attach) throws Exception{
		int result = qnaService.create(boardDTO, attach);
		return "redirect:./list";
	}
}
