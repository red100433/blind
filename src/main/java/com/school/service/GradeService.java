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
		dao.add(grade);
	}

	public void updateGrade(Grade grade) {
		dao.update(grade);
	}

	public void deleteGrade(Grade grade) {
		dao.delete(grade.getStuId(), grade.getSubId());
	}

	public List<String> selectOption(String selectOption) {
		return dao.getAllList(selectOption);
	}

}
