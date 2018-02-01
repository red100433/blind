package com.school.service;

import java.util.List;

import com.school.dao.StudentDao;
import com.school.models.request.StudentRequest;
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

	public void addStudent(StudentRequest stuRequest) {
		init().addStudent(new Student(stuRequest.getName(), stuRequest.getBirth()));
	}

	public void updateStudent(StudentRequest stuRequest) {
		//id값 없음
		init().updateStudent(new Student(stuRequest.getName(), stuRequest.getBirth()));
	}

	public void deleteStudent(StudentRequest stuRequest) {
		//id값 없음
		init().deleteStudent(1);
	}

	public List<Student> getAllSubjects() {
		return init().getAllSubjects();
	}

	private StudentDao init() {
		return new StudentDao();
	}
}