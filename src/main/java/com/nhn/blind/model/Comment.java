package com.nhn.blind.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Comment {
	private Long id;
	private String comment;
	private Timestamp date;
	private Long boardId;
	private int userId;

	public Comment() {}
}
