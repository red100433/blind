package com.nhn.school.service;

import java.util.List;

import com.nhn.school.dao.TeacherDao;
import com.nhn.school.models.vo.Teacher;

public class TeacherService {
	private static TeacherService t;
	private TeacherDao dao = TeacherDao.getInstance();

	public static TeacherService getInstance() {
		synchronized (TeacherService.class) {
			if (t == null) {
				t = new TeacherService();
			}
		}
		return t;
	}

	private TeacherService() {}

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
