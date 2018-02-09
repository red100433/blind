package com.nhn.school.model;

import lombok.Data;
import lombok.NonNull;

/**
 *
 * @author daeyun-jang
 *
 */

@Data
public class Employee {

	private int empId;
	@NonNull
	private String employeeName;
	@NonNull
	private String birth;

	public Employee() {}
}
