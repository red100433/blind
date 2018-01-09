package com.vehicle.models;

import java.io.Serializable;
import java.util.Objects;

public class Teacher implements Serializable {
	int teachId;
	int subjectId;
	String teacherName;
	String birth;

	public Teacher(int subjectId, String teacherName, String birth) {
		this.subjectId = subjectId;
		this.teacherName = teacherName;
		this.birth = birth;
		this.teachId = hashCode();
	}

	public int getId() {
		return teachId;
	}

	public String getteacherName() {
		return teacherName;
	}

	public void setteacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public int getSub() {
		return subjectId;
	}

	public void setSub(int subjectId) {
		this.subjectId = subjectId;
	}

	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}
		if (!(o instanceof Student)) {
			return false;
		}
		Teacher stu = (Teacher)o;
		return teachId == stu.teachId &&
			subjectId == stu.subjectId &&
			Objects.equals(teacherName, stu.teacherName) &&
			Objects.equals(birth, stu.birth);
	}

	@Override
	public int hashCode() {
		return Objects.hash(teacherName, subjectId, birth, teachId);
	}
}
