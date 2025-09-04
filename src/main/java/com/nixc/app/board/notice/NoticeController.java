package com.nixc.app.board.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/api/notice/**")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping
	public Page<NoticeVO> list(Pageable pageable) throws Exception {
		return noticeService.list(pageable);
	}
	
	@ResponseBody
	@GetMapping("{boardNo}")
	public NoticeVO detail(@PathVariable("boardNo") Long boardNo) throws Exception {
		NoticeVO noticeVO = noticeService.detail(boardNo);
//		System.out.println(noticeVO.getBoardFileVOs().getFirst().getSaveName());
		noticeVO.getBoardContent();
		return noticeService.detail(boardNo);
	}
	
	@PostMapping("add")
	@ResponseBody
	public boolean add(NoticeVO noticeVO, MultipartFile[] attaches) throws Exception {
//		noticeVO.setBoardHit(0L);
//		boolean result = false;
//		noticeVO = noticeService.add(noticeVO);
//		
//		if(noticeVO != null) {
//			result = true;
//		}
//		
//		return result;
		System.out.println(attaches);
		
		return false;
	}
	
}
