package com.gguek.app.board.notice.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gguek.app.board.common.dto.BoardDTO;
import com.gguek.app.board.notice.dto.NoticeDTO;

@SpringBootTest
class NoticeMapperTest {

	@Autowired
	private NoticeMapper noticeMapper;
	
	
	@Test
	void testCreate()throws Exception{
		for(int i=0;i<23;i++) {
			NoticeDTO noticeDTO = new NoticeDTO();
			noticeDTO.setBoardTitle("title"+i);
			noticeDTO.setBoardWriter("writer"+i);
			noticeDTO.setBoardContents("contents"+i);
			noticeMapper.create(noticeDTO);
			if (i%3==0) {
				Thread.sleep(500);
			}
			
		}
	}
	
	@Test
	void testList() throws Exception{
		List<BoardDTO> ar = noticeMapper.list();
		assertNotEquals(0, ar.size());
	}

}
