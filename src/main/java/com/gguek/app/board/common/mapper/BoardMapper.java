package com.gguek.app.board.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gguek.app.board.common.dto.BoardDTO;
import com.gguek.app.file.dto.FileDTO;
import com.gguek.app.pager.Pager;


@Mapper
public interface BoardMapper {

	
	
	//getCount
	Long getCount(Pager pager) throws Exception;
	
	//list
	List<BoardDTO> list(Pager pager) throws Exception;
	
	//detail
	BoardDTO detail(BoardDTO boardDTO) throws Exception;
	
	//create
	int create(BoardDTO boardDTO) throws Exception;
	
	//createFile
	int createFile(FileDTO fileDTO) throws Exception;
	
	//update
	int update(BoardDTO boardDTO) throws Exception;
	
	//delete
	int delete(BoardDTO boardDTO) throws Exception;
	
	
	FileDTO fileDetail(FileDTO fileDTO) throws Exception;
}
	