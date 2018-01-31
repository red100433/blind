package com.school.service;

import java.util.List;

import com.school.dao.SubjectDao;
import com.school.models.request.SubjectRequest;
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

	public void addSubject(SubjectRequest subjectRequest) {
		init().addSubject(new Subject(subjectRequest.getName()));
	}

	public void updateSubject(SubjectRequest subjectRequest) {
		// id 값 없음
		init().updateSubject(new Subject(subjectRequest.getName()));
	}

	public void deleteSubject(SubjectRequest subjectRequest) {
		// id 값 없음
		init().deleteSubject(1);
	}

	public List<Subject> getAllSubjects() {
		return init().getAllSubjects();
	}

	private SubjectDao init() {
		return new SubjectDao();
	}
}
