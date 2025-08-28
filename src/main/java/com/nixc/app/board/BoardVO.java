package com.nixc.app.board;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {

	private Long boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private LocalDateTime boardDate;
	private Long boardHit;
	
}
