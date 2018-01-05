package com.vehicle.superintend;

import java.util.UUID;

public class Grade {
	UUID stu_Id;
	UUID sub_Id;
	int grade;

	public UUID getStu_Id() {
		return stu_Id;
	}

	public void setStu_Id(UUID stu_Id) {
		this.stu_Id = stu_Id;
	}

	public UUID getSub_Id() {
		return sub_Id;
	}

	public void setSub_Id(UUID sub_Id) {
		this.sub_Id = sub_Id;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
}
