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
public class Student implements Serializable {
	private static final long serialVersionUID = -918783022995365596L;

	private int stu_Id;
	@NonNull
	private String studentName;
	@NonNull
	private String birth;

	public Student() {}

}
