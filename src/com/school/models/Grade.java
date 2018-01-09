package com.school.models;

import java.io.Serializable;
import java.util.Objects;

public class Grade implements Serializable {

	private String studentName;
	private String subjectName;
	private int grade;

	public Grade(String studentName, String subjectName, int grade) {
		this.studentName = studentName;
		this.subjectName = subjectName;
		this.grade = grade;
	}

	public Grade(String studentName, String subjectName) {
		this.studentName = studentName;
		this.subjectName = subjectName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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
		return Objects.equals(studentName, gradeObj.getStudentName()) &&
			Objects.equals(subjectName, gradeObj.getSubjectName()) &&
			grade == gradeObj.getGrade();
	}

	@Override
	public int hashCode() {
		return Objects.hash(studentName, subjectName, grade);
	}

	@Override
	public String toString() {
		return "Grade [studentName=" + studentName + ", subjectName=" + subjectName + ", grade=" + grade + "]";
	}
}
