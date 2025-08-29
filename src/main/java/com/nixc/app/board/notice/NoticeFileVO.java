package com.nixc.app.board.notice;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "notice_file")
public class NoticeFileVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fileNo;
	private String saveName;
	private String oriName;
	
	@ManyToOne
	@JoinColumn(name = "boardNo")
	@JsonIgnore // JSON 직렬화 할 때 제외
	private NoticeVO noticeVO;
	
}
