package com.gguek.app.board.qna.dto;

import com.gguek.app.file.dto.FileDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaFileDTO extends FileDTO{

	private Long boardNum;
	
}
