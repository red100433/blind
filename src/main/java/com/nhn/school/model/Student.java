package com.nhn.school.model;

import lombok.Data;
import lombok.NonNull;

/**
 *
 * @author daeyun-jang
 *
 */

@Data
public class Student {

	private int stuId;
	@NonNull
	private String studentName;
	@NonNull
	private String birth;

	public Student() {}

}
