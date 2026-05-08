package com.gguek.app.board.qna.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.gguek.app.board.qna.dto.QnaDTO;
import com.gguek.app.board.qna.service.QnaService;
import com.gguek.app.pager.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {

	@Autowired
	private QnaService qnaService;

	@Value("${app.board.qna}")
	private String name;

	@ModelAttribute("name")
	public String getName() {
		return this.name;
	}

	@GetMapping("list")
	public String list(Pager pager, Model model) throws Exception {
		List<BoardDTO> ar = qnaService.list(pager);
		model.addAttribute("list", ar);
		return "board/list";
	}

	@GetMapping("detail")
	public String detail(BoardDTO boardDTO, Model model) throws Exception {
		boardDTO = qnaService.detail(boardDTO);
		model.addAttribute("d", boardDTO);
		return "board/detail";
	}

	@GetMapping("create")
	public String create() throws Exception {
		return "board/create";
	}

	@GetMapping("create2")
	public String create(BoardDTO boardDTO, @RequestParam(name = "attach", required = false) MultipartFile[] attach)
			throws Exception {
		int result = qnaService.create(boardDTO, attach);
		return "redirect:./list";
	}

	@GetMapping("update")
	public String update(QnaDTO qnaDTO, Model model) throws Exception {
		BoardDTO boardDTO = qnaService.detail(qnaDTO);
		model.addAttribute("d", boardDTO);
		return "board/update";
	}

	@PostMapping("update")
	public String update(QnaDTO qnaDTO, @RequestParam("attach") MultipartFile[] attach) throws Exception {
		int result = qnaService.update(qnaDTO, attach);
		return "redirect:./list";
	}

	@PostMapping("delete")
	public String delete(QnaDTO qnaDTO) throws Exception {
		int result = qnaService.delete(qnaDTO);
		return "redirect:./list";
	


	}

	
	
}
