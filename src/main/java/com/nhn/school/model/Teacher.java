package com.nhn.school.model;

import lombok.Data;
import lombok.NonNull;

/**
 *
 * @author daeyun-jang
 *
 */

@Data
public class Teacher {

	private int teacherId;
	@NonNull
	private String teacherName;
	@NonNull
	private String birth;
	private int subId;

	public Teacher() {}
}
