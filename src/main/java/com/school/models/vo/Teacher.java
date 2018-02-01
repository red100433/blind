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

	private int teacher_Id;
	@NonNull
	private String teacherName;
	@NonNull
	private String birth;
	private int sub_Id;

	public Teacher() {}

	public static Teacher of(String teacher_Id, String teacherName, String birth, String sub_Id) {
		if (teacher_Id == null) {
			teacher_Id = "0";
		}
		if (teacherName == null) {
			teacherName = "";
		}
		if (birth == null) {
			birth = "";
		}
		if (sub_Id == null) {
			sub_Id = "0";
		}
		new Teacher();
		return new Teacher(Integer.parseInt(teacher_Id), teacherName, birth, Integer.parseInt(sub_Id));
	}
}
