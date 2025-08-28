package com.nixc.app.board.notice;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest 
class NoticeRepositoryTest {

	@Autowired
	private NoticeRepository noticeRepository;
	
	//@Test
	void test() {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("title3");
		noticeVO.setBoardContent("content3");
		noticeVO.setBoardWriter("writer3");
		
		noticeVO = noticeRepository.save(noticeVO);
		
		assertNotNull(noticeVO);
	}
	
	//@Test
	void test2() {
		NoticeVO noticeVO = new NoticeVO();
		
		Optional<NoticeVO> result = noticeRepository.findById(1L);
		noticeVO = result.get();
		
		assertNotNull(noticeVO);
		System.out.println(noticeVO.getBoardContent());
		System.out.println(noticeVO.getBoardTitle());
		System.out.println(noticeVO.getBoardWriter());
	}
	
	@Test
	void test3() {
		List<NoticeVO> list = noticeRepository.findAll();
		
		if(list.size() != 0 && list != null) {
			for(NoticeVO l : list) {
				System.out.println(l.getBoardContent());
				System.out.println(l.getBoardWriter());
				System.out.println(l.getBoardTitle());
			}
		}
	}
	
	

}
