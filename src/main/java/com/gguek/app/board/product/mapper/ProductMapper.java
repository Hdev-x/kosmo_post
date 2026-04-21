package com.gguek.app.board.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gguek.app.board.product.dto.ProductDTO;
import com.gguek.app.pager.Pager;

@Mapper
public interface ProductMapper {
	
	public Long getCount(Pager pager) throws Exception;
	
	public List<ProductDTO> list(Pager pager) throws Exception;
	
	public ProductDTO detail(ProductDTO productDTO) throws Exception;
	
	public int create(ProductDTO productDTO) throws Exception;
	
	public int update(ProductDTO productDTO) throws Exception;
	
	public int delete(ProductDTO productDTO) throws Exception;
}