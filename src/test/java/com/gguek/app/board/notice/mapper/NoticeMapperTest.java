package com.gguek.app.board.notice.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gguek.app.board.common.dto.BoardDTO;

@SpringBootTest
class NoticeMapperTest {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Test
	void testList() throws Exception{
		List<BoardDTO> ar = noticeMapper.list();
		assertNotEquals(0, ar.size());
	}

}
