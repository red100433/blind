package com.school.service;

import java.util.List;

import com.school.business.TeacherCrudImp;
import com.school.custom.TeacherCrud;
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

	public void writeFileSystem(List<Teacher> teacherList) {
		new TeacherDao().writeDataList(teacherList);
	}

	public void insert(TeacherRequest teacherRequest) {
		List<Teacher> teacherList = select();
		teacherList = init()
			.insert(teacherList, teacherRequest);
		writeFileSystem(teacherList);
	}

	public void update(TeacherRequest teacherRequest) {
		List<Teacher> teacherList = select();
		teacherList = init()
			.update(teacherList, teacherRequest);
		writeFileSystem(teacherList);
	}

	public void update(String name, String changeName) {
		List<Teacher> teacherList = select();
		teacherList = init().update(teacherList, name, changeName);
		writeFileSystem(teacherList);
	}

	public void delete(TeacherRequest teacherRequest) {
		List<Teacher> teacherList = select();
		teacherList = init()
			.delete(teacherList, teacherRequest);
		writeFileSystem(teacherList);
	}

	public void delete(String name) {
		List<Teacher> teacherList = select();
		teacherList = new TeacherCrudImp().delete(teacherList, name);
		writeFileSystem(teacherList);
	}

	public List<Teacher> select() {
		return new TeacherDao().readDataList();
	}

	private TeacherCrud init() {
		return new TeacherCrudImp();
	}

}
