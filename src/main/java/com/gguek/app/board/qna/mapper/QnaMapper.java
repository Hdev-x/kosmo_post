package com.gguek.app.board.qna.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gguek.app.board.common.mapper.BoardMapper;
import com.gguek.app.file.dto.FileDTO;

@Mapper
public interface QnaMapper extends BoardMapper {

	public int fileDelete(FileDTO fileDTO) throws Exception;

	public int fileDeleteFor(List<FileDTO> list) throws Exception;
}
