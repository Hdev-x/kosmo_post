package com.gguek.app.board.qna.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gguek.app.board.common.dto.BoardDTO;
import com.gguek.app.board.notice.dto.NoticeDTO;
import com.gguek.app.board.qna.dto.QnaDTO;
import com.gguek.app.pager.Pager;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class QnaMapperTest {

	@Autowired
	private QnaMapper qnaMapper;
	
//	@Test
//	void testCreate()throws Exception{
//		for(int i=0;i<23;i++) {
//			QnaDTO qnaDTO = new QnaDTO();
//			qnaDTO.setBoardTitle("Q&A title"+i);
//			qnaDTO.setBoardWriter("Q&A writer"+i);
//			qnaDTO.setBoardContents("Q&A contents"+i);
//			qnaMapper.create(qnaDTO);
//			if (i%3==0) {
//				Thread.sleep(500);
//			}
//			
//		}
//		
//		System.out.println("END");
//	}
	
	
	@Test
	void testList() throws Exception{
		Pager pager = new Pager();
		pager.setSearch("7");
		pager.makeOffset();
		List<BoardDTO> ar = qnaMapper.list(pager);
		log.info("{}", ar);
		assertEquals(5, ar.size());
		
	}

}
