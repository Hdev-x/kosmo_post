package com.gguek.app.board.product.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDTO {

	private Long productNum;
	private String productName;
	private String productDesc;
	private String productType;
	private Double productRate;
}