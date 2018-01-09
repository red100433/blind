package com.vehicle.models;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {

	private int studentId;
	private String studentName;
	private String birth;

	public Student(String studentName, String birth) {
		this.studentName = studentName;
		this.birth = birth;
		this.studentId = hashCode();
	}

	public int getId() {
		return studentId;
	}

	public String getstudentName() {
		return studentName;
	}

	public void setstudentName(String studentName) {
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
		return studentId == stuObj.studentId &&
			Objects.equals(studentName, stuObj.studentName) &&
			Objects.equals(birth, stuObj.birth);
	}

	@Override
	public int hashCode() {
		return Objects.hash(studentName, studentId, birth);
	}

}
