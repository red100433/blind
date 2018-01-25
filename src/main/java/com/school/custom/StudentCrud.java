package com.school.custom;

import java.util.List;

import com.school.models.vo.Student;

public interface StudentCrud {

	List<Student> insert(List<Student> list);

	List<Student> update(List<Student> list, String changeName, String changeBirth);

	List<Student> delete(List<Student> list);

}