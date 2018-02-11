package com.nhn.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhn.school.dao.StudentDao;
import com.nhn.school.exception.PeopleCapacity;
import com.nhn.school.model.Student;
import com.nhn.school.model.Type;

@Service
public class StudentService {
	@Autowired
	private StudentDao dao;

	public void save(Student student) {
		if (dao.count() < Type.PERSON_SIZE) {
			dao.save(student);
		} else {
			new PeopleCapacity();
		}
	}

	public void delete(int id) {
		dao.delete(id);
	}

	public List<Student> findAll() {
		return dao.findAll();
	}

}