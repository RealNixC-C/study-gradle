package com.nixc.app.board;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity // 해당 객체가 JPA에서 관리하고 있다라는것을 정의
@Table(name = "notice") // DB에 존재하는 테이블 이름을 매핑, 클래스명이 테이블명이 됨 
public class BoardVO {

	@Id // primary key로 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
	private Long boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private LocalDateTime boardDate;
	private Long boardHit;
	
}
