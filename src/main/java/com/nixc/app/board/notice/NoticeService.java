package com.nixc.app.board.notice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;
	
	public NoticeVO detail(Long id) throws Exception {
		Optional<NoticeVO> result = noticeRepository.findById(id);
		return result.orElseThrow();
	}
	
	public Page<NoticeVO> list(@PageableDefault(size = 10, sort = "reqDt", direction = Direction.DESC) Pageable pageable) throws Exception {
		Page<NoticeVO> result = noticeRepository.findAll(pageable);
		
		return result;
	}
	
}
