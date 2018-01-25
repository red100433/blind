package com.school.custom;

import java.util.List;

import com.school.models.request.TeacherRequest;
import com.school.models.vo.Teacher;

public interface TeacherCrud {

	List<Teacher> insert(List<Teacher> list, TeacherRequest teacherRequest);

	List<Teacher> update(List<Teacher> list, TeacherRequest teacherRequest);

	List<Teacher> delete(List<Teacher> list, TeacherRequest teacherRequest);

	List<Teacher> update(List<Teacher> teacherList, String name, String changeName);

}