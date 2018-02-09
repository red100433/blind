package com.nhn.school.model;

import lombok.Data;
import lombok.NonNull;

/**
 *
 * @author daeyun-jang
 *
 */

@Data
public class Subject {

	private int subId;
	@NonNull
	private String subjectName;

	public Subject() {
	}

}