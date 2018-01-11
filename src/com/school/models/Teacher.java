package com.school.models;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author daeyun-jang
 *
 */
public class Teacher implements Serializable {
	private int teachId;
	private String teacherName;
	private String subjectName;
	private String birth;

	public Teacher(String subjectName, String teacherName, String birth) {
		this.teacherName = teacherName;
		this.subjectName = subjectName;
		this.birth = birth;
		this.teachId = hashCode();
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getTeachId() {
		return teachId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}
		if (!(o instanceof Teacher)) {
			return false;
		}
		Teacher stu = (Teacher)o;
		return teachId == stu.getTeachId() &&
			Objects.equals(subjectName, stu.getSubjectName()) &&
			Objects.equals(teacherName, stu.getTeacherName()) &&
			Objects.equals(birth, stu.getBirth());
	}

	@Override
	public int hashCode() {
		return Objects.hash(teacherName, subjectName, birth, teachId);
	}

	@Override
	public String toString() {
		return "Teacher [teacherName=" + teacherName + ", subjectName=" + subjectName + ", birth=" + birth + "]";
	}
}
