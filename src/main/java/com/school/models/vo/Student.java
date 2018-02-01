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

	private int stu_Id;
	@NonNull
	private String studentName;
	@NonNull
	private String birth;

	public Student() {}

	public static Student of(String stu_Id, String studentName, String birth) {
		if (stu_Id == null) {
			stu_Id = "0";
		}
		if (studentName == null) {
			studentName = "";
		}
		if (birth == null) {
			birth = "";
		}

		return new Student(Integer.parseInt(stu_Id), studentName, birth);
	}

}
