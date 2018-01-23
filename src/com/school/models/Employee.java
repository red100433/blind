package com.school.models;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author daeyun-jang
 *
 */

@Getter
@EqualsAndHashCode
@ToString(exclude = "employeeId")
public class Employee implements Serializable {
	private static final long serialVersionUID = -7708695675154867454L;

	private int employeeId;
	@Setter
	private String employeeName;
	@Setter
	private String birth;

	public Employee(String employeeName, String birth) {
		this.employeeName = employeeName;
		this.birth = birth;
		this.employeeId = hashCode();
	}
}
