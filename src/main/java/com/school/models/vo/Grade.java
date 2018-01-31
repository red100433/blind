package com.school.models.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(exclude = "grade")
@RequiredArgsConstructor
@AllArgsConstructor
public class Grade implements Serializable {
	private static final long serialVersionUID = -6298676113379834491L;

	private int stu_Id;
	private int sub_Id;
	private int grade;
}
