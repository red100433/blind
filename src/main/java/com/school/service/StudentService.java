package com.school.service;

import java.util.List;

import com.school.dao.StudentDao;
import com.school.models.vo.Student;

public class StudentService {
	private static StudentService t;
	private StudentDao dao = StudentDao.getInstance();

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
		dao.addStudent(student);
	}

	public void updateStudent(Student student) {
		dao.updateStudent(student);
	}

	public void deleteStudent(Student student) {
		dao.deleteStudent(student.getStuId());
	}

	public List<Student> getAllSubjects() {
		return dao.getAllSubjects();
	}

}