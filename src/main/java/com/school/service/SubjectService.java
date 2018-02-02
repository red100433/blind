package com.school.service;

import java.util.List;

import com.school.dao.SubjectDao;
import com.school.models.vo.Subject;

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
		dao.addSubject(subject);
	}

	public void updateSubject(Subject subject) {
		dao.updateSubject(subject);
	}

	public void deleteSubject(Subject subject) {
		dao.deleteSubject(subject.getSubId());
	}

	public List<Subject> getAllSubjects() {
		return dao.getAllSubjects();
	}
}
