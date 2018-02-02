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

	private int subId;
	@NonNull
	private String subjectName;

	public Subject() {}

	public static Subject of(String subId, String subjectName) {
		if (subId == null) {
			subId = "0";
		}
		if (subjectName == null) {
			subjectName = "";
		}

		return new Subject(Integer.parseInt(subId), subjectName);
	}
}