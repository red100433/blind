package com.school.models.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author daeyun-jang
 *
 */

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Employee implements Serializable {
	private static final long serialVersionUID = -7708695675154867454L;

	private int emp_Id;
	@NonNull
	private String employeeName;
	@NonNull
	private String birth;

	public Employee() {}

	public static Employee of(String emp_Id, String employeeName, String birth) {
		if (emp_Id == null) {
			emp_Id = "0";
		}
		if (employeeName == null) {
			employeeName = "";
		}
		if (birth == null) {
			birth = "";
		}

		return new Employee(Integer.parseInt(emp_Id), employeeName, birth);
	}
}
