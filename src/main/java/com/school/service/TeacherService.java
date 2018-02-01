package com.school.service;

import java.util.List;

import com.school.dao.TeacherDao;
import com.school.models.request.TeacherRequest;
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

	public void addTeacher(TeacherRequest teacherRequest) {
		//sub_id 없음
		init()
			.addTeacher(new Teacher(teacherRequest.getName(), teacherRequest.getBirth()));
	}

	public void updateTeacher(TeacherRequest teacherRequest) {
		//id 없음, sub_id 없음
		init()
			.updateTeacher(new Teacher(teacherRequest.getName(), teacherRequest.getBirth()));
	}

	public void deleteTeacher(TeacherRequest teacherRequest) {
		//id 없음
		init().deleteTeacher(1);
	}

	public List<Teacher> getAllTeachers() {
		return new TeacherDao().getAllTeachers();
	}

	private TeacherDao init() {
		return new TeacherDao();
	}

}
