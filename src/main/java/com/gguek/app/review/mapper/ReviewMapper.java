package com.gguek.app.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gguek.app.review.dto.ReviewDTO;

@Mapper
public interface ReviewMapper {
	
	List<ReviewDTO> list(ReviewDTO reviewDTO) throws Exception;
	
	int create(ReviewDTO reviewDTO) throws Exception;
	
	int update(ReviewDTO reviewDTO) throws Exception;
	
	int delete(ReviewDTO reviewDTO) throws Exception;

}
