package com.school.inter.custom;

import java.util.List;

import com.school.models.vo.Grade;

public interface GradeCrud {

	List<Grade> insert(List<Grade> list, int grade);

	List<Grade> update(List<Grade> list, String changeStudentName, String changeSubjectName,
		int changeGrade);

	List<Grade> delete(List<Grade> list);

}