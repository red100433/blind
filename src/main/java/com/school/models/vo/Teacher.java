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
public class Teacher implements Serializable {
	private static final long serialVersionUID = -8068141543663245843L;

	private int teacher_Id;
	@NonNull
	private String teacherName;
	@NonNull
	private String birth;
	private int sub_Id;

	public Teacher() {}
}
