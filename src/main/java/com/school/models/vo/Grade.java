package com.school.models.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Grade implements Serializable {
	private static final long serialVersionUID = -6298676113379834491L;

	private int stu_Id;
	private int sub_Id;
	private int grade;

	public Grade() {}
}
