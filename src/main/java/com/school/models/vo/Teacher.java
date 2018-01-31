package com.school.models.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NonNull;

/**
 *
 * @author daeyun-jang
 *
 */

@Data
public class Teacher implements Serializable {
	private static final long serialVersionUID = -8068141543663245843L;

	private int teacher_Id;
	@NonNull
	private String teacherName;

	private int sub_Id;
	@NonNull
	private String birth;
}
