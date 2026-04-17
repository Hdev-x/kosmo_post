package com.gguek.app.board.notice.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gguek.app.board.common.dto.BoardDTO;
import com.gguek.app.board.notice.dto.NoticeDTO;
import com.gguek.app.pager.Pager;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class NoticeMapperTest {

	@Autowired
	private NoticeMapper noticeMapper;
	
	
//	@Test
//	void testCreate()throws Exception{
//		for(int i=0;i<23;i++) {
//			NoticeDTO noticeDTO = new NoticeDTO();
//			noticeDTO.setBoardTitle("title"+i);
//			noticeDTO.setBoardWriter("writer"+i);
//			noticeDTO.setBoardContents("contents"+i);
//			noticeMapper.create(noticeDTO);
//			if (i%3==0) {
//				Thread.sleep(500);
//			}
//			
//		}
//	}
	
	@Test
	void testList() throws Exception{
		Pager pager = new Pager();
		pager.setSearch("9");
		pager.setPage(2L);
		pager.makeOffset();
		List<BoardDTO> ar = noticeMapper.list(pager);
		log.info("{}", ar);
		assertEquals(5, ar.size());
		
	}

}
