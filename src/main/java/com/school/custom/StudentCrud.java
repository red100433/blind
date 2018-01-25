package com.school.custom;

import java.util.List;

import com.school.models.request.StudentRequest;
import com.school.models.vo.Student;

public interface StudentCrud {

	List<Student> delete(List<Student> list, StudentRequest stuRequest);

	List<Student> update(List<Student> list, StudentRequest stuRequest);

	List<Student> insert(List<Student> list, StudentRequest stuRequest);

}