package com.gguek.app.board.product.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gguek.app.board.product.dto.ProductDTO;
import com.gguek.app.board.product.service.ProductService;
import com.gguek.app.pager.Pager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Value("${app.product}")
	private String name;
	
	@ModelAttribute("name")
	public String getName() {
		return this.name;
	}
	
	@GetMapping("/list")
	public String list(Pager pager, Model model) throws Exception {
		
		List<ProductDTO> ar = productService.list(pager);
		
		model.addAttribute("list", ar);
		
		return "product/list";  // 변경: board/list → product/list
	}
	
}