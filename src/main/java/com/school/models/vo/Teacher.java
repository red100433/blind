package com.school.models.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author daeyun-jang
 *
 */

@Data
@AllArgsConstructor
@RequiredArgsConstructor
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
		}
		if (subId == null) {
			subId = "0";
		}
		return new Teacher(Integer.parseInt(teacherId), teacherName, birth, Integer.parseInt(subId));
	}
}
