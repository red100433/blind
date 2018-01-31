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
public class Student implements Serializable {
	private static final long serialVersionUID = -918783022995365596L;

	private int stu_Id;
	@NonNull
	private String studentName;
	@NonNull
	private String birth;

}
