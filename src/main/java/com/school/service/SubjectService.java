package com.school.service;

import java.util.List;

import com.school.business.SubjectCrudImp;
import com.school.custom.SubjectCrud;
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

	private SubjectService() {}

	public void writeFileSystem(List<Subject> subList) {
		new SubjectDao().writeDataList(subList);
	}

	public void insert(SubjectRequest subjectRequest) {
		List<Subject> subList = select();
		subList = init().insert(subList, subjectRequest);
		writeFileSystem(subList);
	}

	public void update(SubjectRequest subjectRequest) {
		List<Subject> subList = select();
		subList = init().update(subList, subjectRequest);
		writeFileSystem(subList);
	}

	public void delete(SubjectRequest subjectRequest) {
		List<Subject> subList = select();
		subList = init().delete(subList, subjectRequest);
		writeFileSystem(subList);
	}

	public List<Subject> select() {
		return new SubjectDao().readDataList();
	}

	private SubjectCrud init() {
		return new SubjectCrudImp();
	}
}
