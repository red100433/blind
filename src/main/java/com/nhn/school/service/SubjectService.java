package com.nhn.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhn.school.dao.SubjectDao;
import com.nhn.school.models.vo.Subject;

@Service
public class SubjectService {
	@Autowired
	private SubjectDao dao;

	public void addSubject(Subject subject) {
		dao.add(subject);
	}

	public void updateSubject(Subject subject) {
		dao.update(subject);
	}

	public void deleteSubject(Subject subject) {
		dao.delete(subject.getSubId());
	}

	public List<Subject> getAllSubjects() {
		return dao.getAllList();
	}
}
