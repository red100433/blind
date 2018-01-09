package com.school.models;

import java.io.Serializable;
import java.util.Objects;

public class Subject implements Serializable {
	int subjectId;
	String subjectName;

	public Subject(String subjectName) {
		this.subjectName = subjectName;
		this.subjectId = hashCode();
	}

	public int getId() {
		return subjectId;
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
		return subjectId == subObj.subjectId &&
			Objects.equals(subjectName, subObj.subjectName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(subjectId, subjectName);
	}

}