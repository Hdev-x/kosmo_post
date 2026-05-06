package com.gguek.app.review.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewDTO {

	private Long reviewNum;
	private String username;
	private String reviewContents;
	private Long reviewStar;
	private OffsetDateTime reviewDate;
	private Long productNum;
	private OffsetDateTime updateDate;
	
}
