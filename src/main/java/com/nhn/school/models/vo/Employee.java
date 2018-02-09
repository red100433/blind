package com.nhn.school.models.vo;

import java.io.Serializable;

import com.nhn.school.exception.Validator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 *
 * @author daeyun-jang
 *
 */

@Getter
@AllArgsConstructor
@ToString
public class Employee implements Serializable {
	private static final long serialVersionUID = -7708695675154867454L;

	private int empId;
	@NonNull
	private String employeeName;
	@NonNull
	private String birth;

	public Employee() {}

	public static Employee of(String empId, String employeeName, String birth) {
		if (empId == null) {
			empId = "0";
		}
		if (employeeName == null) {
			employeeName = "";
		}

		if (birth == null) {
			birth = "";
		} else {
			Validator.isDateValid(birth);
		}
			

		return new Employee(Integer.parseInt(empId), employeeName, birth);
	}
}
