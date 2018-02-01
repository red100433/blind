package com.school.service;

import java.util.List;

import com.school.dao.TeacherDao;
import com.school.models.vo.Teacher;

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
		dao.addTeacher(teacher);
	}

	public void updateTeacher(Teacher teacher) {
		dao.updateTeacher(teacher);
	}

	public void deleteTeacher(Teacher teacher) {
		dao.deleteTeacher(teacher.getTeacher_Id());
	}

	public List<Teacher> getAllTeachers() {
		return dao.getAllTeachers();
	}

}
