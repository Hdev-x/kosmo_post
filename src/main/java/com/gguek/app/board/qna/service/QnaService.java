package com.gguek.app.board.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gguek.app.board.common.dto.BoardDTO;
import com.gguek.app.board.common.service.BoardService;
import com.gguek.app.board.qna.dto.QnaFileDTO;
import com.gguek.app.board.qna.mapper.QnaMapper;
import com.gguek.app.file.dto.FileDTO;
import com.gguek.app.file.manager.FileManager;
import com.gguek.app.pager.Pager;

@Service
public class QnaService implements BoardService{
	
	@Autowired
	private QnaMapper qnaMapper;

	@Autowired
	private FileManager fileManager;
	
	@Value("${app.board.qna}")
	private String name;
	

	@Override
	public List<BoardDTO> list(Pager pager) throws Exception {
		pager.init(qnaMapper.getCount(pager));
		pager.makeOffset();
		return qnaMapper.list(pager);
	}

	@Override
	public BoardDTO detail(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return qnaMapper.detail(boardDTO);
	}

	@Override
	public int create(BoardDTO boardDTO, MultipartFile [] attach) throws Exception {
		// 1. 게시판 테이블에 글을 추가
		int result = qnaMapper.create(boardDTO);
		
		if(attach == null) return result;
		
		//2. 파일 저장
		for (MultipartFile multipartFile : attach) {
			if(multipartFile.isEmpty()) continue;
			// a.파일을 HDD에 저장
			String filename = fileManager.fileSave(name, multipartFile);
			// b.파일의 정보들을 DB에 저장
			QnaFileDTO qnaFileDTO = new QnaFileDTO();
			qnaFileDTO.setBoardNum(boardDTO.getBoardNum());
			qnaFileDTO.setOriginName(multipartFile.getOriginalFilename());
			qnaFileDTO.setFileName(filename);
			
			result = qnaMapper.createFile(qnaFileDTO);
		}
		
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO, MultipartFile [] attach) throws Exception {
		int result = qnaMapper.update(boardDTO);
		return result;
	}

	@Override
	public int delete(BoardDTO boardDTO) throws Exception {
		//파일명 조회
		boardDTO = qnaMapper.detail(boardDTO);
		
		//HDD에서 파일 삭제
		for (FileDTO fileDTO:boardDTO.getList()) {
			fileManager.fileDelete(name, fileDTO);
			// qnaMapper.fileDelete(fileDTO);
		}
		qnaMapper.fileDeleteFor(boardDTO.getList());
		
		//DB에서 삭제
		int result = qnaMapper.delete(boardDTO);
		return result;
	}
	
	

}
