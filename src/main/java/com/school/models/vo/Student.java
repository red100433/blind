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
public class Student implements Serializable {
	private static final long serialVersionUID = -918783022995365596L;

	private int stuId;
	@NonNull
	private String studentName;
	@NonNull
	private String birth;

	public Student() {}

	public static Student of(String stuId, String studentName, String birth) {
		if (stuId == null) {
			stuId = "0";
		}
		if (studentName == null) {
			studentName = "";
		}
		if (birth == null) {
			birth = "";
		}

		return new Student(Integer.parseInt(stuId), studentName, birth);
	}

}
