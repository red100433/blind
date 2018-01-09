package com.vehicle.models;

import java.io.Serializable;
import java.util.Objects;

public class Grade implements Serializable {
	private int stu_Id;
	private int sub_Id;
	private int grade;

	public Grade(int stu_Id, int sub_Id, int grade) {
		this.stu_Id = stu_Id;
		this.sub_Id = sub_Id;
		this.grade = grade;
	}

	public Grade(int stu_Id, int sub_Id) {
		this.stu_Id = stu_Id;
		this.sub_Id = sub_Id;
	}

	public int getStu_Id() {
		return stu_Id;
	}

	public void setStu_Id(int stu_Id) {
		this.stu_Id = stu_Id;
	}

	public int getSub_Id() {
		return sub_Id;
	}

	public void setSub_Id(int sub_Id) {
		this.sub_Id = sub_Id;
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
		return stu_Id == gradeObj.stu_Id &&
			sub_Id == gradeObj.sub_Id &&
			grade == gradeObj.grade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(stu_Id, sub_Id, grade);
	}

}
