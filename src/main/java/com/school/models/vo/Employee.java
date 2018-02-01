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
}
