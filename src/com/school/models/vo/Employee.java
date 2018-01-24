package com.school.models.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 *
 * @author daeyun-jang
 *
 */

@Data
@EqualsAndHashCode
public class Employee implements Serializable {
	private static final long serialVersionUID = -7708695675154867454L;

	@NonNull
	private String employeeName;
	@NonNull
	private String birth;
}
