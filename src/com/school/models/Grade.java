package com.school.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(exclude = "grade")
@RequiredArgsConstructor
@AllArgsConstructor

public class Grade implements Serializable {

	@NonNull
	private String studentName;
	@NonNull
	private String subjectName;
	private int grade;
}
