package com.nhn.school.model;

import lombok.Data;

@Data
public class Grade {

	private int id;
	private int stuId;
	private int subId;
	private int score;

	public Grade() {}

}
