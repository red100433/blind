package com.nhn.school.models.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Grade implements Serializable {
	private static final long serialVersionUID = -6298676113379834491L;

	private int stuId;
	private int subId;
	private int score;

	public Grade() {}

	public static Grade of(String stuId, String subId, String score) {
		if (stuId == null) {
			stuId = "0";
		}
		if (subId == null) {
			subId = "0";
		}
		if (score == null) {
			score = "0";
		}
		if (Integer.parseInt(score) < 0) {
			score = "0";
		} else if (Integer.parseInt(score) > 100) {
			score = "100";
		}

		return new Grade(Integer.parseInt(stuId), Integer.parseInt(subId), Integer.parseInt(score));
	}
}
