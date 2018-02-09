package com.nhn.school.models.vo;

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
		}else {
			Validator.isDateValid(birth);
		}

		return new Student(Integer.parseInt(stuId), studentName, birth);
	}

}
