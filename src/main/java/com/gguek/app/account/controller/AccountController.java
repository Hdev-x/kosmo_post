package com.gguek.app.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gguek.app.account.dto.AccountDTO;
import com.gguek.app.account.service.AccountService;
import com.gguek.app.member.dto.MemberDTO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/account/*")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("create")
	public void create()throws Exception{}
	
	@PostMapping("create")
	public String create(AccountDTO accountDTO, HttpSession session)throws Exception{
		Object obj = session.getAttribute("member");
		MemberDTO memberDTO = (MemberDTO)obj;
		
		accountDTO.setUsername(memberDTO.getUsername());
		
		int result = accountService.create(accountDTO);
		
		return "redirect:/";
		
	}

}