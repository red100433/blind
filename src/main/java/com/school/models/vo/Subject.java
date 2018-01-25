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
public class Subject implements Serializable {
	private static final long serialVersionUID = -2910796104540445977L;

	@NonNull
	private String subjectName;
}