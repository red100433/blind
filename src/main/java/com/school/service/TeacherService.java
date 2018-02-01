package com.school.service;

import java.util.List;

import com.school.dao.TeacherDao;
import com.school.models.vo.Teacher;

public class TeacherService {
	private static TeacherService t;

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
		init().addTeacher(teacher);
	}

	public void updateTeacher(Teacher teacher) {
		init().updateTeacher(teacher);
	}

	public void deleteTeacher(Teacher teacher) {
		init().deleteTeacher(teacher.getTeacher_Id());
	}

	public List<Teacher> getAllTeachers() {
		return new TeacherDao().getAllTeachers();
	}

	private TeacherDao init() {
		return new TeacherDao();
	}

}
