package com.school.service;

import java.util.List;

import com.school.business.GradeCrudImp;
import com.school.custom.GradeCrud;
import com.school.dao.GradeDao;
import com.school.models.request.GradeRequest;
import com.school.models.vo.Grade;

public class GradeService {
	private static GradeService t;

	public static GradeService getInstance() {
		synchronized (GradeService.class) {
			if (t == null) {
				t = new GradeService();
			}
		}
		return t;
	}

	private GradeService() {}

	public void writeFileSystem(List<Grade> gradeList) {
		new GradeDao().writeDataList(gradeList);
	}

	public void insert(GradeRequest gradeRequest) {
		List<Grade> gradeList = select();
		gradeList = init().insert(gradeList,
			gradeRequest);
		writeFileSystem(gradeList);
	}

	public void update(GradeRequest gradeRequest) {
		List<Grade> gradeList = select();
		gradeList = init()
			.update(gradeList, gradeRequest);
		writeFileSystem(gradeList);
	}

	public void update(String tempName, String changeName) {
		List<Grade> gradeList = select();
		gradeList = new GradeCrudImp().update(gradeList, tempName, changeName);
		writeFileSystem(gradeList);
	}

	public void delete(GradeRequest gradeRequest) {
		List<Grade> gradeList = select();
		gradeList = init().delete(gradeList, gradeRequest);
		writeFileSystem(gradeList);
	}

	public void delete(String name) {
		List<Grade> gradeList = select();
		gradeList = new GradeCrudImp().delete(gradeList, name);
		writeFileSystem(gradeList);
	}

	public List<Grade> select() {
		return new GradeDao().readDataList();
	}

	private GradeCrud init() {
		return new GradeCrudImp();
	}

	public List<String> selectOption(String selectOption) {

		return init().selectOption(select(), selectOption);
	}

}
