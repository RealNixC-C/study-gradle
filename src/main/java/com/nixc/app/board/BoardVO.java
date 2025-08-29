package com.nixc.app.board;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public class BoardVO {

	@Id // primary key로 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
	private Long boardNo;
	
	// 컬럼명이 다른경우 명시(scale : 소수점자리)
	@Column(name = "boardTitle", nullable = false, unique = true, length = 255, insertable = true, updatable = true)
	private String boardTitle;
	private String boardWriter;
	@Lob
//	@Column(columnDefinition = "LONGTEXT")
	private String boardContent;
	@Temporal(TemporalType.TIMESTAMP) // 시간 매핑
	@CreationTimestamp
	private LocalDateTime boardDate;
//	@Column(columnDefinition = "BIGINT DEFAULT 0" ,insertable = false)
	@Column
	@ColumnDefault(value = "0") // 위에거랑 동일
	private Long boardHit;
	
	@Transient // 테이블의 컬럼과 매핑관계를 제외
	private String kind;
	
}
