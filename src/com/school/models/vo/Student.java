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
public class Student implements Serializable {
	private static final long serialVersionUID = -918783022995365596L;

	@NonNull
	private String studentName;
	@NonNull
	private String birth;

}
