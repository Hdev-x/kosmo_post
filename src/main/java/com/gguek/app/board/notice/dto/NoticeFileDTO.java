package com.gguek.app.board.notice.dto;

import com.gguek.app.file.dto.FileDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeFileDTO extends FileDTO{

	private Long boardNum;
	
}
