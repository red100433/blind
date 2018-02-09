package com.nhn.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhn.school.dao.StudentDao;
import com.nhn.school.model.Student;

@Service
public class StudentService {
	@Autowired
	private StudentDao dao;

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