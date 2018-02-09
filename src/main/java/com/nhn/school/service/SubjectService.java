package com.nhn.school.service;

import java.util.List;

import com.nhn.school.dao.SubjectDao;
import com.nhn.school.models.vo.Subject;

public class SubjectService {
	private static SubjectService t;
	private SubjectDao dao = SubjectDao.getInstance();

	public static SubjectService getInstance() {
		synchronized (SubjectService.class) {
			if (t == null) {
				t = new SubjectService();
			}
		}
		return t;
	}

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
