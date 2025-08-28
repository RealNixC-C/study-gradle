package com.nixc.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/notice/**")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@ResponseBody
	@GetMapping("{boardNo}")
	public NoticeVO detail(@PathVariable("boardNo") Long boardNo) throws Exception {
		return noticeService.detail(boardNo);
	}
	
	@GetMapping
	public List<NoticeVO> list() throws Exception {
		return noticeService.list();
	}
	
}
