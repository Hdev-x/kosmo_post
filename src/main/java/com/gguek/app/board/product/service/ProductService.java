package com.gguek.app.board.product.service;

import java.util.List;
import com.gguek.app.board.product.dto.ProductDTO;
import com.gguek.app.pager.Pager;

public interface ProductService {
	
	public List<ProductDTO> list(Pager pager) throws Exception;
	
	public ProductDTO detail(ProductDTO productDTO) throws Exception;
	
	public int create(ProductDTO productDTO) throws Exception;
	
	public int update(ProductDTO productDTO) throws Exception;
	
	public int delete(ProductDTO productDTO) throws Exception;
}