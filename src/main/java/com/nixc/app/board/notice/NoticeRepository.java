package com.nixc.app.board.notice;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// 첫 번째 제네릭 (매핑할 VO 이름)
// 두 번째 제네릭 (PRIMARY KEY의 데이터 타입)
public interface NoticeRepository extends JpaRepository<NoticeVO, Long> {
	
	public List<NoticeVO> findByBoardTitleLike(String search, Pageable pageable);
	
}