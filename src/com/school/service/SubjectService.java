package com.school.service;

import java.util.List;

import com.school.business.crud.SubjectCrud;
import com.school.dao.SubjectDao;
import com.school.models.request.SubjectRequest;
import com.school.models.vo.Subject;

public class SubjectService {
	private static SubjectService t;
	private List<Subject> subList;

	public static SubjectService getInstance() {
		synchronized (SubjectService.class) {
			if (t == null) {
				t = new SubjectService();
			}
		}
		return t;
	}

	private SubjectService() {
		this.subList = new SubjectDao().readDataList();
	}

	public void writeFileSystem() {
		new SubjectDao().writeDataList(subList);
	}

	public void insert(SubjectRequest subjectRequest) {
		this.subList = new SubjectCrud(subjectRequest.getName()).insert(subList);
		writeFileSystem();
	}

	public void update(SubjectRequest subjectRequest) {
		this.subList = new SubjectCrud(subjectRequest.getName()).update(subList, subjectRequest.getChangeName());
		writeFileSystem();
	}

	public void delete(SubjectRequest subjectRequest) {
		this.subList = new SubjectCrud(subjectRequest.getName()).delete(subList);
		writeFileSystem();
	}

	public List<Subject> select() {
		return this.subList;
	}
}
