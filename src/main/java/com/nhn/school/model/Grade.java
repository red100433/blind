package com.nhn.school.model;

import lombok.Data;

@Data
public class Grade {

	private int id;
	Student student;
	Subject subject;
	private int score;

	public Grade() {}

}
