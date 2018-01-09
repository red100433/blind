package com.vehicle.models;

import java.io.Serializable;
import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}
		if (!(o instanceof Student)) {
			return false;
		}
		Teacher stu = (Teacher)o;
		return teach_id == stu.teach_id &&
			sub_Id == stu.sub_Id &&
			Objects.equals(name, stu.name) &&
			Objects.equals(birth, stu.birth);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, sub_Id, birth, teach_id);
	}
}
