package com.gguek.app.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gguek.app.file.manager.FileManager;
import com.gguek.app.member.dto.MemberDTO;
import com.gguek.app.member.dto.ProfileDTO;
import com.gguek.app.member.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private FileManager fileManager;

	// 회원가입 페이지
	@GetMapping("join")
	public void join() throws Exception {
	}

	// 회원가입 처리
	@PostMapping("join")
	public String joinPost(MemberDTO memberDTO, @RequestParam("file") MultipartFile file) throws Exception {
		int result = memberService.join(memberDTO, file);
		return "redirect:/member/login";
	}

	// 로그인 페이지
	@GetMapping("login")
	public void login() throws Exception {
	}

	// 로그인 처리
	@PostMapping("login")
	public String detail(MemberDTO memberDTO, HttpSession session, Model model) throws Exception {
	    
	    memberDTO = memberService.detail(memberDTO);

	    if (memberDTO != null) {
	        session.setAttribute("member", memberDTO);
	        return "redirect:/";
	    } else {
	        model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
	        return "member/login";
	    }
	}

	// 로그아웃
	@GetMapping("logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:/";
	}

}