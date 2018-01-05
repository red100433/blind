package com.vehicle.personnel;

import java.util.UUID;

public class Teacher {
	UUID teach_id;
	UUID sub_Id;
	String name;
	String birth;

	public UUID getId() {
		return teach_id;
	}

	public void setId(UUID teach_id) {
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

	public UUID getSub() {
		return sub_Id;
	}

	public void setSub(UUID sub_Id) {
		this.sub_Id = sub_Id;
	}
}
