package com.vehicle.personnel;

import java.util.UUID;

public class Employee {
	UUID emp_Id;
	String name;
	String birth;

	public UUID getId() {
		return emp_Id;
	}

	public void setId(UUID emp_Id) {
		this.emp_Id = emp_Id;
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
