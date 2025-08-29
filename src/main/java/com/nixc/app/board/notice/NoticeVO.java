package com.nixc.app.board.notice;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nixc.app.board.BoardVO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "notice") 
public class NoticeVO extends BoardVO {

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "noticeVO", cascade = CascadeType.ALL)
	private List<NoticeFileVO> boardFileVOs;
	
}
