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
public class Subject implements Serializable {

	private static final long serialVersionUID = -2910796104540445977L;

	private int sub_Id;
	@NonNull
	private String subjectName;

	public Subject() {}
}