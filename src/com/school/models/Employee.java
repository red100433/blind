package com.school.models;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Serializable {
	private int employeeId;
	private String employeeName;
	private String birth;

	public Employee(String employeeName, String birth) {
		this.employeeName = employeeName;
		this.birth = birth;
		this.employeeId = hashCode();
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
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
		return employeeId == empObj.getEmployeeId() &&
			Objects.equals(employeeName, empObj.getEmployeeName()) &&
			Objects.equals(birth, empObj.getBirth());
	}

	@Override
	public int hashCode() {
		return Objects.hash(employeeName, employeeId, birth);
	}

	@Override
	public String toString() {
		return "Employee [employeeName=" + employeeName + ", birth=" + birth + "]";
	}

}
