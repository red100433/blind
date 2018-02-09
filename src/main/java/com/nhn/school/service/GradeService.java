package com.nhn.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhn.school.dao.GradeDao;
import com.nhn.school.models.vo.Grade;

@Service
public class GradeService {

	@Autowired
	private GradeDao dao;

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
