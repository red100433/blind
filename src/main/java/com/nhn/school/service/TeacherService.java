package com.nhn.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhn.school.dao.TeacherDao;
import com.nhn.school.models.vo.Teacher;

@Service
public class TeacherService {

	@Autowired
	private TeacherDao dao;

	public void addTeacher(Teacher teacher) {
		dao.add(teacher);
	}

	public void updateTeacher(Teacher teacher) {
		dao.update(teacher);
	}

	public void deleteTeacher(Teacher teacher) {
		dao.delete(teacher.getTeacherId());
	}

	public List<Teacher> getAllTeachers() {
		return dao.getAllList();
	}

}
