package com.school.service;

import java.util.List;

import com.school.business.crud.GradeCrudImp;
import com.school.custom.GradeCrud;
import com.school.dao.GradeDao;
import com.school.models.request.GradeRequest;
import com.school.models.vo.Grade;

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
		this.gradeList = init(gradeRequest).insert(gradeList,
			gradeRequest.getGrade());
		writeFileSystem();
	}

	public void update(GradeRequest gradeRequest) {
		this.gradeList = init(gradeRequest)
			.update(gradeList, gradeRequest.getChangeName(), gradeRequest.getChangeSubject(),
				gradeRequest.getChangeGrade());
		writeFileSystem();
	}

	public void update(String tempName, String changeName) {
		this.gradeList = new GradeCrudImp().update(gradeList, tempName, changeName);
		writeFileSystem();
	}

	public void delete(GradeRequest gradeRequest) {
		this.gradeList = init(gradeRequest).delete(gradeList);
		writeFileSystem();
	}

	public void delete(String name) {
		this.gradeList = new GradeCrudImp().delete(gradeList, name);
		writeFileSystem();
	}

	public List<Grade> select() {
		return this.gradeList;
	}

	private GradeCrud init(GradeRequest gradeRequest) {
		return new GradeCrudImp(gradeRequest.getName(), gradeRequest.getSubject());
	}

	public List<String> selectOption(String selectOption, GradeRequest gradeRequest) {

		return init(gradeRequest).selectOption(gradeList, selectOption);
	}

}
