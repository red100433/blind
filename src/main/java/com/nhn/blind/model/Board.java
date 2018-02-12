package com.nhn.blind.model;

import java.math.BigInteger;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Board {
	private Long id;
	private String title;
	private String content;
	private Timestamp Date;
	private int userId;
	
	public Board() {}
}
