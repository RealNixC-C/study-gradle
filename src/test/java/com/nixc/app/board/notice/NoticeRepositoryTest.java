package com.nixc.app.board.notice;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.nixc.app.board.BoardVO;

@SpringBootTest 
@Transactional
class NoticeRepositoryTest {

	@Autowired
	private NoticeRepository noticeRepository;
	
	@Test
	void test() {
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardTitle("title1");
		boardVO.setBoardContent("content1");
		boardVO.setBoardWriter("writer1");
		
		boardVO = noticeRepository.save(boardVO);
		
		assertNotNull(boardVO);
	}
	
	@Test
	void test2() {
		BoardVO boardVO = new BoardVO();
		
		Optional<BoardVO> result = noticeRepository.findById(1L);
		boardVO = result.get();
		
		assertNotNull(boardVO);
		System.out.println(boardVO.getBoardContent());
		System.out.println(boardVO.getBoardTitle());
		System.out.println(boardVO.getBoardWriter());
	}

}
