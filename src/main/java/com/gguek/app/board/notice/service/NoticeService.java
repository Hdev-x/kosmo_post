package com.gguek.app.board.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gguek.app.board.common.dto.BoardDTO;
import com.gguek.app.board.common.service.BoardService;
import com.gguek.app.board.notice.dto.NoticeFileDTO;
import com.gguek.app.board.notice.mapper.NoticeMapper;
import com.gguek.app.file.dto.FileDTO;
import com.gguek.app.file.manager.FileManager;
import com.gguek.app.pager.Pager;

@Service
//@Transactional(rollbackFor = Exception.class)
public class NoticeService implements BoardService{
	
	@Autowired
	private NoticeMapper noticeMapper;

	@Autowired
	private FileManager fileManager;
	
	@Value("${app.board.notice}")
	private String name;


	@Override
	public List<BoardDTO> list(Pager pager) throws Exception {
		pager.init(noticeMapper.getCount(pager));
		pager.makeOffset();
		return noticeMapper.list(pager);
	}

	@Override
	public BoardDTO detail(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.detail(boardDTO);
	}

	@Override
	public int create(BoardDTO boardDTO, MultipartFile [] attach) throws Exception {
		// 1. 게시판 테이블에 글을 추가
		int result = noticeMapper.create(boardDTO);
		
		if(attach == null) return result;
		
		// 2. 파일을 HDD에 저장
		for (MultipartFile multipartFile : attach) {
			if(multipartFile.isEmpty()) continue;
			String fileName = fileManager.fileSave(name, multipartFile);	
			// 3. 파일의 정보들을 DB에 저장			
			NoticeFileDTO noticeFileDTO = new NoticeFileDTO();
			noticeFileDTO.setBoardNum(boardDTO.getBoardNum());
			noticeFileDTO.setOriginName(multipartFile.getOriginalFilename());
			noticeFileDTO.setFileName(fileName);
			
			result = noticeMapper.createFile(noticeFileDTO);
		}
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO, MultipartFile [] attach) throws Exception {
		int result = noticeMapper.update(boardDTO);
		return result;
	}

	@Override
	public int delete(BoardDTO boardDTO) throws Exception {
		//파일명 조회
		boardDTO = noticeMapper.detail(boardDTO);
		
		//HDD에서 파일 삭제
		for (FileDTO fileDTO:boardDTO.getList()) {
			fileManager.fileDelete(name, fileDTO);
		}
		//DB에서 삭제
		int result = noticeMapper.delete(boardDTO);
		return result;
	}

	@Override
	public FileDTO fileDetail(FileDTO fileDTO) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.fileDetail(fileDTO);
	}
	
	
	
	

}
