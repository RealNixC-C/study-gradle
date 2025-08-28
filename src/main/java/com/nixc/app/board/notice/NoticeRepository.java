package com.nixc.app.board.notice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nixc.app.board.BoardVO;

// 첫 번째 제네릭 (매핑할 VO 이름)
// 두 번째 제네릭 (PRIMARY KEY의 데이터 타입)
public interface NoticeRepository extends JpaRepository<BoardVO, Long> {

}
