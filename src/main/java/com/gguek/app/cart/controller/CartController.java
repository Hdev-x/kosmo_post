package com.gguek.app.cart.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gguek.app.cart.dto.CartDTO;
import com.gguek.app.cart.service.CartService;
import com.gguek.app.member.dto.MemberDTO;
import com.gguek.app.product.dto.ProductDTO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart/*")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("list")
	public void list()throws Exception{
		
	}
	
	@GetMapping("cart-list")
	public void list(HttpSession session, Model model)throws Exception{
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		List<ProductDTO> ar = cartService.list(memberDTO);
		model.addAttribute("list", ar);
	}
	
	@PostMapping("create")
	public String create(HttpSession session, CartDTO cartDTO, Model model) {
	    MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
	    
	    if (memberDTO == null) {
	        model.addAttribute("result", -1); // 로그인이 안 되어 있으면 -1 반환
	        return "commons/ajaxResult";
	    }

	    try {
	        cartDTO.setUsername(memberDTO.getUsername());
	        int result = cartService.create(cartDTO);
	        model.addAttribute("result", result);
	    } catch (org.springframework.dao.DuplicateKeyException e) {
	        // DB에서 중복 키 에러가 발생하면 숫자 2를 반환
	        model.addAttribute("result", 2);
	    } catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("result", 0);
	    }
	    
	    return "commons/ajaxResult";
	}
	
//	@PostMapping("delete")
//	public String delete(HttpSession session, CartDTO cartDTO, Model model)throws Exception{
//		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
//		cartDTO.setUsername(memberDTO.getUsername());
//		int result = cartService.delete(cartDTO);
//		model.addAttribute("result", 1);
//		return "commons/ajaxResult";
//		
//	}
	
	@PostMapping("delete")
	public String delete(HttpSession session, @RequestParam("productNum") Long [] productNum, Model model)throws Exception{
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		List<CartDTO> ar = new ArrayList<>();
		
		for (Long l : productNum) {
			CartDTO cartDTO = new CartDTO();
			cartDTO.setProductNum(l);
			cartDTO.setUsername(memberDTO.getUsername());
			ar.add(cartDTO);
		}
		int result = cartService.delete(ar);
		model.addAttribute("result", 1);
		return "commons/ajaxResult";
		
	}
	
	

}