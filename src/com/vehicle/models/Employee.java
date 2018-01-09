package com.vehicle.models;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Serializable {
	int emp_Id;
	String name;
	String birth;

	public int getId() {
		return emp_Id;
	}

	public void setId(int emp_Id) {
		this.emp_Id = emp_Id;
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
		Employee empObj = (Employee)o;
		return emp_Id == empObj.emp_Id &&
			Objects.equals(name, empObj.name) &&
			Objects.equals(birth, empObj.birth);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, emp_Id, birth);
	}

}
