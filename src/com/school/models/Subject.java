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
@ToString(exclude = "subjectId")
public class Subject implements Serializable {
	private int subjectId;
	@Setter
	private String subjectName;

	public Subject(String subjectName) {
		this.subjectName = subjectName;
		this.subjectId = hashCode();
	}
}