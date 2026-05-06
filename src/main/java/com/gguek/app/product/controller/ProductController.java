package com.gguek.app.product.controller;

import java.util.List;

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

import com.gguek.app.product.dto.ProductDTO;
import com.gguek.app.product.service.ProductService;
import com.gguek.app.pager.Pager;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Value("${app.product}")
	private String name;
	
	@ModelAttribute("name")
	public String getName() {
		return this.name;
	}
	
	@GetMapping("list")
	public void list(Pager pager, Model model)throws Exception{
		List<ProductDTO> list = productService.list(pager);
		model.addAttribute("list", list);
	}
	
	@GetMapping("detail")
	public void detail(ProductDTO productDTO, Model model)throws Exception{
		productDTO = productService.detail(productDTO);
		model.addAttribute("dto", productDTO);
	}
	
	@GetMapping("create")
	public void create()throws Exception{}
	
	@PostMapping("create")
	public String create(ProductDTO productDTO, @RequestParam("attach") MultipartFile attach)throws Exception{
		int result = productService.create(productDTO, attach);
		
		return "redirect:./list";
	}

}