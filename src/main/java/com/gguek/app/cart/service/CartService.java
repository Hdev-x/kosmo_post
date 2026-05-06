package com.gguek.app.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gguek.app.cart.dto.CartDTO;
import com.gguek.app.cart.mapper.CartMapper;
import com.gguek.app.member.dto.MemberDTO;
import com.gguek.app.product.dto.ProductDTO;

@Service
public class CartService {
	
	@Autowired
	private CartMapper cartMapper;
	
	public int delete(List<CartDTO> ar)throws Exception{
		return cartMapper.delete(ar);
	}
	
//	public int delete(CartDTO cartDTO)throws Exception{
//		return cartMapper.delete(cartDTO);
//	}
	
	public List<ProductDTO> list(MemberDTO memberDTO)throws Exception{
		return cartMapper.list(memberDTO);
	}
	
	public int create(CartDTO cartDTO)throws Exception{
		return cartMapper.create(cartDTO);
	}

}
