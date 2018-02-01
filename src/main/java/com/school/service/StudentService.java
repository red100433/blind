package com.school.service;

import java.util.List;

import com.school.dao.StudentDao;
import com.school.models.vo.Student;

public class StudentService {
	private static StudentService t;

	public static StudentService getInstance() {
		synchronized (StudentService.class) {
			if (t == null) {
				t = new StudentService();
			}
		}
		return t;
	}

	private StudentService() {}

	public void addStudent(Student student) {
		init().addStudent(student);
	}

	public void updateStudent(Student student) {
		init().updateStudent(student);
	}

	public void deleteStudent(Student student) {
		init().deleteStudent(student.getStu_Id());
	}

	public List<Student> getAllSubjects() {
		return init().getAllSubjects();
	}

	private StudentDao init() {
		return new StudentDao();
	}
}