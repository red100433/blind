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
@ToString(exclude = "teachId")
public class Teacher implements Serializable {
	private static final long serialVersionUID = -8068141543663245843L;

	private int teachId;
	@Setter
	private String teacherName;
	@Setter
	private String subjectName;
	@Setter
	private String birth;

	public Teacher(String subjectName, String teacherName, String birth) {
		this.teacherName = teacherName;
		this.subjectName = subjectName;
		this.birth = birth;
		this.teachId = hashCode();
	}

}
