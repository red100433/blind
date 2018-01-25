package com.school.custom;

import java.util.List;

import com.school.models.request.GradeRequest;
import com.school.models.vo.Grade;

public interface GradeCrud {

	List<Grade> update(List<Grade> list, GradeRequest gradeRequest);

	List<Grade> delete(List<Grade> list, GradeRequest gradeRequest);

	List<Grade> insert(List<Grade> list, GradeRequest gradeRequest);

	List<String> selectOption(List<Grade> list, String selectOption);

}