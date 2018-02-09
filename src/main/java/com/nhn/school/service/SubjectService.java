package com.nhn.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhn.school.dao.SubjectDao;
import com.nhn.school.model.Subject;

@Service
public class SubjectService {
	@Autowired
	private SubjectDao dao;

	public void save(Subject subject) {
		dao.save(subject);
	}

	public void delete(int id) {
		dao.delete(id);
	}

	public List<Subject> findAll() {
		return dao.findAll();
	}
}
