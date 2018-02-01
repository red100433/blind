package com.school.service;

import java.util.List;

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

	public void addGrade(GradeRequest gradeRequest) {
		//stu_Id, sub_Id 값 없음
		init().addGrade(new Grade(1, 1, 0));
	}

	public void updateGrade(GradeRequest gradeRequest) {
		//stu_Id, sub_Id 값 없음
		init().updateGrade(new Grade(1, 1, 0));
	}

	public void deleteGrade(GradeRequest gradeRequest) {
		//stu_Id, sub_Id 값 없음
		init().deleteGrade(1, 1);
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
