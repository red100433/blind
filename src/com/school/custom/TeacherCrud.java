package com.school.custom;

import java.util.List;

import com.school.models.vo.Teacher;

public interface TeacherCrud {

	List<Teacher> insert(List<Teacher> list);

	List<Teacher> update(List<Teacher> list, String changeName, String changeBirth, String changeSubject);

	List<Teacher> delete(List<Teacher> list);

}