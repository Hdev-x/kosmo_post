package com.gguek.app.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gguek.app.review.dto.ReviewDTO;
import com.gguek.app.review.mapper.ReviewMapper;

@Service
public class ReviewService {

	@Autowired
	private ReviewMapper reviewMapper;

	
	public List<ReviewDTO> list(ReviewDTO reviewDTO) throws Exception {
		List<ReviewDTO> ar = reviewMapper.list(reviewDTO);
		return ar;
	}

	public int create(ReviewDTO reviewDTO) throws Exception {
		int result = reviewMapper.create(reviewDTO);
		return result;
	}
	
	public int update(ReviewDTO reviewDTO) throws Exception {
		int result = reviewMapper.update(reviewDTO);
		return result;
	}
	
	public int delete(ReviewDTO reviewDTO) throws Exception {
		int result = reviewMapper.delete(reviewDTO);
		return result;
	}
}
