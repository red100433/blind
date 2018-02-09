package com.nhn.school.model;

import java.io.Serializable;

import com.nhn.school.exception.Validator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 *
 * @author daeyun-jang
 *
 */

@Getter
@AllArgsConstructor
@ToString
public class Teacher implements Serializable {
	private static final long serialVersionUID = -8068141543663245843L;

	private int teacherId;
	@NonNull
	private String teacherName;
	@NonNull
	private String birth;
	private int subId;

	public Teacher() {}

	public static Teacher of(String teacherId, String teacherName, String birth, String subId) {
		if (teacherId == null) {
			teacherId = "0";
		}
		if (teacherName == null) {
			teacherName = "";
		}
		if (birth == null) {
			birth = "";
		} else {
			Validator.isDateValid(birth);
		}
		if (subId == null) {
			subId = "0";
		}
		return new Teacher(Integer.parseInt(teacherId), teacherName, birth, Integer.parseInt(subId));
	}
}
