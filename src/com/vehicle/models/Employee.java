package com.vehicle.models;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Serializable {
	int employeeId;
	String employeeName;
	String birth;

	public int getId() {
		return employeeId;
	}

	public void setId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getemployeeName() {
		return employeeName;
	}

	public void setemployeeName(String employeeName) {
		this.employeeName = employeeName;
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
		return employeeId == empObj.employeeId &&
			Objects.equals(employeeName, empObj.employeeName) &&
			Objects.equals(birth, empObj.birth);
	}

	@Override
	public int hashCode() {
		return Objects.hash(employeeName, employeeId, birth);
	}

}
