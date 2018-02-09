package com.nhn.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhn.school.dao.GradeDao;
import com.nhn.school.model.Grade;

@Service
public class GradeService {

	@Autowired
	private GradeDao dao;

	public void save(Grade grade) {
		dao.save(grade);
	}

	public void save(Grade grade, int id) {
		dao.save(grade, id);
	}

	public void delete(int id) {
		dao.delete(id);
	}

	public List<String> findAll() {
		return dao.findAll();
	}

}
