package com.nhn.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhn.school.dao.TeacherDao;
import com.nhn.school.exception.PeopleCapacity;
import com.nhn.school.model.Teacher;
import com.nhn.school.model.Type;

@Service
public class TeacherService {

	@Autowired
	private TeacherDao dao;

	public void save(Teacher teacher) {
		if (dao.count() < Type.PERSON_SIZE) {
			dao.save(teacher);
		} else {
			new PeopleCapacity();
		}
	}

	public void delete(int id) {
		dao.delete(id);
	}

	public List<Teacher> findAll() {
		return dao.findAll();
	}

}
