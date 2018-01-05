package com.vehicle.personnel;

import java.util.UUID;

public class Student {
	UUID stu_Id;
	String name;
	String birth;

	public UUID getId() {
		return stu_Id;
	}

	public void setId(UUID stu_Id) {
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
