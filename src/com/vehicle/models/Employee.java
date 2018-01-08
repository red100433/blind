package com.vehicle.models;

public class Employee {
	int emp_Id;
	String name;
	String birth;

	public int getId() {
		return emp_Id;
	}

	public void setId(int emp_Id) {
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
