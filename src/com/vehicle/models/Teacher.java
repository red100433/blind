package com.vehicle.models;

import java.io.Serializable;

public class Teacher implements Serializable {
	int teach_id;
	int sub_Id;
	String name;
	String birth;

	public int getId() {
		return teach_id;
	}

	public void setId(int teach_id) {
		this.teach_id = teach_id;
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

	public int getSub() {
		return sub_Id;
	}

	public void setSub(int sub_Id) {
		this.sub_Id = sub_Id;
	}
}
