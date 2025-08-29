package com.nixc.app.board.notice;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@Transactional
class NoticeRepositoryTest {

	@Autowired
	private NoticeRepository noticeRepository;

	@Autowired
	private NoticeFileRepository noticeFileRepository;
	
	//@Test
	void test() throws Exception {
		
		// 간단한 방법1
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setBoardTitle("제목1");
//		noticeVO.setBoardContent("내용1");
//		noticeVO.setBoardWriter("작성자1");
//		noticeRepository.save(noticeVO);
//		
//		NoticeFileVO noticeFileVO = new NoticeFileVO();
//		noticeFileVO.setBoardNo(1L);
//		noticeFileVO.setOriName("ori");
//		noticeFileVO.setOriName("save");
//		noticeFileVO = noticeFileRepository.save(noticeFileVO);
//		
//		assertNotNull(noticeFileVO);
		
		// 한번에 하는방법 2
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("제목4");
		noticeVO.setBoardContent("내용4");
		noticeVO.setBoardWriter("작성자4");
		
//		NoticeFileVO noticeFileVO = new NoticeFileVO();
//		noticeFileVO.setNoticeVO(noticeVO);
//		noticeFileVO.setOriName("원본이름.jpg");
//		noticeFileVO.setSaveName("저장이름.jpg");
//		
//		List<NoticeFileVO> list = new ArrayList<>();
//		list.add(noticeFileVO);
//		
//		noticeVO.setBoardFileVOs(list);
		
		noticeVO = noticeRepository.save(noticeVO);
		assertNotNull(noticeVO);
		
	}
	
	//@Test
	void test2() {
		
		Optional<NoticeVO> result = noticeRepository.findById(3L);
		NoticeVO noticeVO = result.get();
		log.info("{}", noticeVO.getBoardNo());
		log.info("{}", noticeVO.getBoardFileVOs().getFirst().getSaveName());
		assertNotNull(noticeVO);
	}
	
	//@Test
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
	
	@Test
	void test4() {
		Pageable pageable = PageRequest.of(1, 10, Sort.by("boardNo").descending());		
		List<NoticeVO> list = noticeRepository.findByBoardTitleLike("%%", pageable);
		log.info("{}", list.size());
		
		list.forEach((e)->{
			log.info("{}", e.getBoardContent());
		});
		
	}
	
	

}
