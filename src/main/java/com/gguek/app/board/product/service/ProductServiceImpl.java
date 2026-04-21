package com.gguek.app.board.product.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gguek.app.board.product.dto.ProductDTO;
import com.gguek.app.board.product.mapper.ProductMapper;
import com.gguek.app.pager.Pager;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public List<ProductDTO> list(Pager pager) throws Exception {
		
		pager.init(productMapper.getCount(pager));
		pager.makeOffset();
		
		return productMapper.list(pager);
	}
	
	@Override
	public ProductDTO detail(ProductDTO productDTO) throws Exception {
		return productMapper.detail(productDTO);
	}
	
	@Override
	public int create(ProductDTO productDTO) throws Exception {
		return productMapper.create(productDTO);
	}
	
	@Override
	public int update(ProductDTO productDTO) throws Exception {
		return productMapper.update(productDTO);
	}
	
	@Override
	public int delete(ProductDTO productDTO) throws Exception {
		return productMapper.delete(productDTO);
	}
	
}