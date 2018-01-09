package com.vehicle.models;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {

	private int stu_Id;
	private String name;
	private String birth;

	public Student(String name, String birth) {
		this.name = name;
		this.birth = birth;
		this.stu_Id = hashCode();
	}

	public int getId() {
		return stu_Id;
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

	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}
		if (!(o instanceof Student)) {
			return false;
		}
		Student stuObj = (Student)o;
		return stu_Id == stuObj.stu_Id &&
			Objects.equals(name, stuObj.name) &&
			Objects.equals(birth, stuObj.birth);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, stu_Id, birth);
	}

}
