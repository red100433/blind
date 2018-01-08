package com.vehicle.models;

import java.io.Serializable;

public class Student implements Serializable {
	int stu_Id;
	String name;
	String birth;

	public int getId() {
		return stu_Id;
	}

	public void setId(int stu_Id) {
		this.stu_Id = stu_Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}
}
