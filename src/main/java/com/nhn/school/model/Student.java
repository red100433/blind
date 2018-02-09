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

	private int id;
	@NonNull
	private String name;
	@NonNull
	private String birth;

	public Student() {}

}
