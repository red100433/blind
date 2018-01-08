package com.vehicle.models;

import java.io.Serializable;

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

		if (!(o instanceof Grade)) {
			return false;
		}

		Grade c = (Grade)o;

		return Double.compare(this.stu_Id, c.stu_Id) == 0
			&& Double.compare(this.sub_Id, c.sub_Id) == 0;
	}

}
