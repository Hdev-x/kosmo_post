package com.gguek.app.board.qna.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gguek.app.board.common.dto.BoardDTO;

@SpringBootTest
class QnaMapperTest {

	@Autowired
	private QnaMapper qnaMapper;
	
	@Test
	void testList() throws Exception{
		List<BoardDTO> ar = qnaMapper.list();
		assertNotEquals(0, ar.size());
	}

}
