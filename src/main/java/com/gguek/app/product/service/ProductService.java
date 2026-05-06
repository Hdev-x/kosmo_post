package com.gguek.app.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gguek.app.product.dto.ProductDTO;
import com.gguek.app.product.dto.ProductFileDTO;
import com.gguek.app.product.mapper.ProductMapper;
import com.gguek.app.file.manager.FileManager;
import com.gguek.app.pager.Pager;

@Service
public class ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private FileManager fileManager;
	
	
	@Value("${app.product}")
	private String name;
	
	public List<ProductDTO> list(Pager pager)throws Exception{
		pager.init(productMapper.getCount(pager));
		pager.makeOffset();
		
		return productMapper.list(pager);
		
	}
	
	public ProductDTO detail(ProductDTO productDTO)throws Exception{
		return productMapper.detail(productDTO);
	}
	
	public int create(ProductDTO productDTO, MultipartFile file)throws Exception{
		int result = productMapper.create(productDTO);
		
		if(file != null && !file.isEmpty()) {
			String fileName = fileManager.fileSave(name, file);
			ProductFileDTO productFileDTO = new ProductFileDTO();
			productFileDTO.setFileName(fileName);
			productFileDTO.setOriginName(file.getOriginalFilename());
			productFileDTO.setProductNum(productDTO.getProductNum());
			productMapper.createFile(productFileDTO);
		}
		
		return result;
		
	}
	
	
}