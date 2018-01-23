package com.school.service;

import java.util.List;

import com.school.business.crud.SubjectCrudImp;
import com.school.dao.SubjectDao;
import com.school.inter.custom.SubjectCrud;
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
		this.subList = init(subjectRequest).insert(subList);
		writeFileSystem();
	}

	public void update(SubjectRequest subjectRequest) {
		this.subList = init(subjectRequest).update(subList, subjectRequest.getChangeName());
		writeFileSystem();
	}

	public void delete(SubjectRequest subjectRequest) {
		this.subList = init(subjectRequest).delete(subList);
		writeFileSystem();
	}

	public List<Subject> select() {
		return this.subList;
	}

	private SubjectCrud init(SubjectRequest subjectRequest) {
		return new SubjectCrudImp(subjectRequest.getName());
	}
}
