package com.school.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
@Builder
public class TeacherRequest {
	private String name;
	private String birth;
	private String subject;
	private String changeName;
	private String changeBirth;
	private String changeSuject;
}
