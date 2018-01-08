package com.vehicle.models;

import java.io.Serializable;

public class Grade implements Serializable {
	int stu_Id;
	int sub_Id;
	int grade;

	public Grade(int stu_Id, int sub_Id, int grade) {
		this.stu_Id = stu_Id;
		this.sub_Id = sub_Id;
		this.grade = grade;
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
}
