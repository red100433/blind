package com.school.models;

import java.io.Serializable;
import java.util.Objects;

public class Grade implements Serializable {
	private int studentId;
	private int subjectId;
	private int grade;

	public Grade(int studentId, int subjectId, int grade) {
		this.studentId = studentId;
		this.subjectId = subjectId;
		this.grade = grade;
	}

	public Grade(int studentId, int subjectId) {
		this.studentId = studentId;
		this.subjectId = subjectId;
	}

	public int getstudentId() {
		return studentId;
	}

	public void setstudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getsubjectId() {
		return subjectId;
	}

	public void setsubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}
		if (!(o instanceof Student)) {
			return false;
		}
		Grade gradeObj = (Grade)o;
		return studentId == gradeObj.studentId &&
			subjectId == gradeObj.subjectId &&
			grade == gradeObj.grade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(studentId, subjectId, grade);
	}

}
