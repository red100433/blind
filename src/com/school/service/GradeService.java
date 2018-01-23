package com.school.service;

import java.util.List;

import com.school.business.crud.GradeCrud;
import com.school.dao.GradeDao;
import com.school.models.Grade;
import com.school.models.request.GradeRequest;

public class GradeService {
	private static GradeService t;
	private List<Grade> gradeList;

	public static GradeService getInstance() {
		synchronized (GradeService.class) {
			if (t == null) {
				t = new GradeService();
			}
		}
		return t;
	}

	private GradeService() {
		this.gradeList = new GradeDao().readDataList();
	}

	public void writeFileSystem() {
		new GradeDao().writeDataList(gradeList);
	}

	public void insert(GradeRequest gradeRequest) {
		this.gradeList = new GradeCrud(gradeRequest.getName(), gradeRequest.getSubject()).insert(gradeList,
			gradeRequest.getGrade());
		writeFileSystem();
	}

	public void update(GradeRequest gradeRequest) {
		this.gradeList = new GradeCrud(gradeRequest.getName(), gradeRequest.getSubject())
			.update(gradeList, gradeRequest.getChangeName(), gradeRequest.getChangeSubject(),
				gradeRequest.getChangeGrade());
		writeFileSystem();
	}

	public void delete(GradeRequest gradeRequest) {
		this.gradeList = new GradeCrud(gradeRequest.getName(), gradeRequest.getSubject()).delete(gradeList);
		writeFileSystem();
	}

	public List<Grade> select() {
		return this.gradeList;
	}
}
