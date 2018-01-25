package com.school.custom;

import java.util.List;

import com.school.models.vo.Subject;

public interface SubjectCrud {

	List<Subject> insert(List<Subject> list);

	List<Subject> update(List<Subject> list, String changeName);

	List<Subject> delete(List<Subject> list);

}