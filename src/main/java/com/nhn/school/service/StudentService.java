package com.nhn.school.service;

import java.util.List;

import com.nhn.school.dao.StudentDao;
import com.nhn.school.models.vo.Student;

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
		dao.add(student);
	}

	public void updateStudent(Student student) {
		dao.update(student);
	}

	public void deleteStudent(Student student) {
		dao.delete(student.getStuId());
	}

	public List<Student> getAllSubjects() {
		return dao.getAllList();
	}

}