package com.gguek.app.board.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gguek.app.board.common.dto.BoardDTO;


@Mapper
public interface BoardMapper {

	
	
	//getCount
	void getCount() throws Exception;
	
	//list
	List<BoardDTO> list() throws Exception;
	
	//detail
	BoardDTO detail(BoardDTO boardDTO) throws Exception;
	
	//create
	int create(BoardDTO boardDTO) throws Exception;
	
	//update
	int update(BoardDTO boardDTO) throws Exception;
	
	//delete
	int delete(BoardDTO boardDTO) throws Exception;
}
