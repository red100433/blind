package com.school.models.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author daeyun-jang
 *
 */

@Data
@EqualsAndHashCode
@ToString(exclude = "subjectId")
public class Subject implements Serializable {
	private static final long serialVersionUID = -2910796104540445977L;

	private String subjectName;

	public Subject(String subjectName) {
		this.subjectName = subjectName;
	}

}