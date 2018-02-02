package com.school.service;

import java.util.List;

import com.school.dao.GradeDao;
import com.school.models.vo.Grade;

public class GradeService {
	private static GradeService t;
	private GradeDao dao = GradeDao.getInstance();

	public static GradeService getInstance() {
		synchronized (GradeService.class) {
			if (t == null) {
				t = new GradeService();
			}
		}
		return t;
	}

	private GradeService() {}

	public void addGrade(Grade grade) {
		dao.addGrade(grade);
	}

	public void updateGrade(Grade grade) {
		dao.updateGrade(grade);
	}

	public void deleteGrade(Grade grade) {
		dao.deleteGrade(grade.getStuId(), grade.getSubId());
	}

	public List<Grade> getAllGrades() {
		return dao.getAllGrades();
	}

	public List<String> selectOption(String selectOption) {
		return dao.selectOption(selectOption);
	}

}
