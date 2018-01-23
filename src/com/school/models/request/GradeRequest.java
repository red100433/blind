package com.school.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
@Builder
public class GradeRequest {
	String name;
	String subject;
	int grade;
	String changeName;
	String changeSubject;
	int changeGrade;
}