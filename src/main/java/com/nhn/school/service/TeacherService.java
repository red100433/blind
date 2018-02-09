package com.nhn.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhn.school.dao.TeacherDao;
import com.nhn.school.model.Teacher;

@Service
public class TeacherService {

	@Autowired
	private TeacherDao dao;

	public void save(Teacher teacher) {
		dao.save(teacher);
	}

	public void save(Teacher teacher, int id) {
		dao.save(teacher);
	}

	public void delete(int id) {
		dao.delete(id);
	}

	public List<Teacher> findAll() {
		return dao.findAll();
	}

}
