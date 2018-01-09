package com.school.models;

import java.io.Serializable;
import java.util.Objects;

public class Subject implements Serializable {
	private int subjectId;
	private String subjectName;

	public Subject(String subjectName) {
		this.subjectName = subjectName;
		this.subjectId = hashCode();
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getSubjectId() {
		return subjectId;
	}

	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}
		if (!(o instanceof Subject)) {
			return false;
		}
		Subject subObj = (Subject)o;
		return subjectId == subObj.getSubjectId() &&
			Objects.equals(subjectName, subObj.getSubjectName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(subjectId, subjectName);
	}

	@Override
	public String toString() {
		return "Subject [subjectName=" + subjectName + "]";
	}

}