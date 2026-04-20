package com.gguek.app.board.common.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gguek.app.board.common.dto.BoardDTO;
import com.gguek.app.pager.Pager;

public interface BoardService {


	// list
	List<BoardDTO> list(Pager pager) throws Exception;

	// detail
	BoardDTO detail(BoardDTO boardDTO) throws Exception;

	// create
	int create(BoardDTO boardDTO, MultipartFile [] attach) throws Exception;

	// update
	int update(BoardDTO boardDTO, MultipartFile [] attach) throws Exception;

	// delete
	int delete(BoardDTO boardDTO) throws Exception;

}
