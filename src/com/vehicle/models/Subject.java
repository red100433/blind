package com.vehicle.models;

import java.io.Serializable;
import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}
		if (!(o instanceof Student)) {
			return false;
		}
		Subject subObj = (Subject)o;
		return sub_Id == subObj.sub_Id &&
			Objects.equals(subjectName, subObj.subjectName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sub_Id, subjectName);
	}

}