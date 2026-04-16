package com.gguek.app.board.qna.dto;

import com.gguek.app.board.common.dto.BoardDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnaDTO extends BoardDTO{
	
	private Long boardRef;
	private Long boardStep;
	private Long boardDepth;

}
