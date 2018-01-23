package com.school.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
@Builder
public class StudentRequest {
	private String name;
	private String birth;
	private String changeName;
	private String changeBirth;
}
