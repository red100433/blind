package com.school.models.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Grade implements Serializable {
	private static final long serialVersionUID = -6298676113379834491L;

	private int stu_Id;
	private int sub_Id;
	private int grade;

	public Grade() {}

	public static Grade of(String stu_Id, String sub_Id, String grade) {
		if (stu_Id == null) {
			stu_Id = "0";
		}
		if (sub_Id == null) {
			sub_Id = "0";
		}
		if (grade == null | Integer.parseInt(grade) < 0) {
			grade = "0";
		} else if (Integer.parseInt(grade) > 100) {
			grade = "100";
		}

		return new Grade(Integer.parseInt(stu_Id), Integer.parseInt(sub_Id), Integer.parseInt(grade));
	}
}
