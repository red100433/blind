package com.vehicle.models;

import java.io.Serializable;

public class Subject implements Serializable {
	int sub_Id;
	String subjectName;

	public Subject(int sub_Id, String subjectName) {
		super();
		this.sub_Id = sub_Id;
		this.subjectName = subjectName;
	}

	public int getId() {
		return sub_Id;
	}

	public void setId(int sub_Id) {
		this.sub_Id = sub_Id;
	}

	public String getName() {
		return subjectName;
	}

	public void setName(String subjectName) {
		this.subjectName = subjectName;
	}
}