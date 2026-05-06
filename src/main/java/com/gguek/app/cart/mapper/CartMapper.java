package com.gguek.app.cart.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gguek.app.cart.dto.CartDTO;
import com.gguek.app.member.dto.MemberDTO;
import com.gguek.app.product.dto.ProductDTO;

@Mapper
public interface CartMapper {
	
	public int delete(List<CartDTO> ar)throws Exception;
	
//	public int delete(CartDTO cartDTO)throws Exception;
	
	public int create(CartDTO cartDTO) throws Exception;
	
	public List<ProductDTO> list (MemberDTO memberDTO)throws Exception;

}
