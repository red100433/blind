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

	public void save(Student student) {
		dao.save(student);
	}

	public void delete(int id) {
		dao.delete(id);
	}

	public List<Student> findAll() {
		return dao.findAll();
	}

}