package com.school.models;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author daeyun-jang
 *
 */
public class Student implements Serializable {

	private int studentId;
	private String studentName;
	private String birth;

	public Student(String studentName, String birth) {
		this.studentName = studentName;
		this.birth = birth;
		this.studentId = hashCode();
	}

	public int getStudentId() {
		return studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}
		if (!(o instanceof Student)) {
			return false;
		}
		Student stuObj = (Student)o;
		return studentId == stuObj.getStudentId() &&
			Objects.equals(studentName, stuObj.getStudentName()) &&
			Objects.equals(birth, stuObj.getBirth());
	}

	@Override
	public int hashCode() {
		return Objects.hash(studentName, studentId, birth);
	}

	@Override
	public String toString() {
		return "Student [studentName=" + studentName + ", birth=" + birth + "]";
	}

}
