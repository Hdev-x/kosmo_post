package com.gguek.app.product.dto;

import com.gguek.app.file.dto.FileDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductFileDTO extends FileDTO {

	private Long productNum;
	
}
