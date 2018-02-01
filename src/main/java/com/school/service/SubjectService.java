package com.school.service;

import java.util.List;

import com.school.dao.SubjectDao;
import com.school.models.vo.Subject;

public class SubjectService {
	private static SubjectService t;

	public static SubjectService getInstance() {
		synchronized (SubjectService.class) {
			if (t == null) {
				t = new SubjectService();
			}
		}
		return t;
	}

	public void addSubject(Subject subject) {
		init().addSubject(subject);
	}

	public void updateSubject(Subject subject) {
		init().updateSubject(subject);
	}

	public void deleteSubject(Subject subject) {
		init().deleteSubject(subject.getSub_Id());
	}

	public List<Subject> getAllSubjects() {
		return init().getAllSubjects();
	}

	private SubjectDao init() {
		return new SubjectDao();
	}
}
