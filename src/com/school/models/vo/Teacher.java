package com.school.models.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 *
 * @author daeyun-jang
 *
 */

@Data
@EqualsAndHashCode
@ToString
public class Teacher implements Serializable {
	private static final long serialVersionUID = -8068141543663245843L;

	@NonNull
	private String teacherName;
	@NonNull
	private String subjectName;
	@NonNull
	private String birth;
}
