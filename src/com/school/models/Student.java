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
@ToString(exclude = "studentId")
public class Student implements Serializable {
	private static final long serialVersionUID = -918783022995365596L;

	private int studentId;
	@Setter
	private String studentName;
	@Setter
	private String birth;

	public Student(String studentName, String birth) {
		this.studentName = studentName;
		this.birth = birth;
		this.studentId = hashCode();
	}
}
