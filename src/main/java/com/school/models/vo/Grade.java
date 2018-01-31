package com.school.models.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(exclude = "grade")
@RequiredArgsConstructor
@AllArgsConstructor
public class Grade implements Serializable {
	private static final long serialVersionUID = -6298676113379834491L;

	private int stu_Id;
	@NonNull
	private String studentName;
	@NonNull
	private String subjectName;
	private int grade;

	public Grade(String studentName, String subjectName, int grade) {
		this.studentName = studentName;
		this.subjectName = subjectName;
		this.grade = grade;
	}
}
