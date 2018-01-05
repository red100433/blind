package com.vehicle.personnel;

import java.util.UUID;

public class Subject {
	UUID sub_Id;
	String subjectName;

	public UUID getId() {
		return sub_Id;
	}

	public void setId(UUID sub_Id) {
		this.sub_Id = sub_Id;
	}

	public String getName() {
		return subjectName;
	}

	public void setName(String subjectName) {
		this.subjectName = subjectName;
	}
}