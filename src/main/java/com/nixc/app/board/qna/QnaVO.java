package com.nixc.app.board.qna;

import com.nixc.app.board.BoardVO;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "qna")
public class QnaVO extends BoardVO {

	private Long boardRef;
	private Long boardStep;
	private Long boardDepth;
	
}
