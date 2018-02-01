package com.school.service;

import java.util.List;

import com.school.dao.GradeDao;
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

	public void addGrade(Grade grade) {
		//stu_Id, sub_Id 값 없음
		init().addGrade(grade);
	}

	public void updateGrade(Grade grade) {
		//stu_Id, sub_Id 값 없음
		init().updateGrade(grade);
	}

	public void deleteGrade(Grade grade) {
		//stu_Id, sub_Id 값 없음
		init().deleteGrade(grade.getStu_Id(), grade.getSub_Id());
	}

	public List<Grade> getAllGrades() {
		return init().getAllGrades();
	}

	private GradeDao init() {
		return new GradeDao();
	}

	public List<String> selectOption(String selectOption) {

		return init().selectOption(selectOption);
	}

}
