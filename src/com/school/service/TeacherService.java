package com.school.service;

import java.util.List;

import com.school.business.crud.TeacherCrudImp;
import com.school.custom.TeacherCrud;
import com.school.dao.TeacherDao;
import com.school.models.request.TeacherRequest;
import com.school.models.vo.Teacher;

public class TeacherService {
	private static TeacherService t;
	private List<Teacher> teacherList;

	public static TeacherService getInstance() {
		synchronized (TeacherService.class) {
			if (t == null) {
				t = new TeacherService();
			}
		}
		return t;
	}

	private TeacherService() {
		this.teacherList = new TeacherDao().readDataList();
	}

	public void writeFileSystem() {
		new TeacherDao().writeDataList(teacherList);
	}

	public void insert(TeacherRequest teacherRequest) {
		this.teacherList = init(teacherRequest)
			.insert(teacherList);
		writeFileSystem();
	}

	public void update(TeacherRequest teacherRequest) {
		this.teacherList = init(teacherRequest)
			.update(teacherList, teacherRequest.getChangeName(), teacherRequest.getChangeBirth(),
				teacherRequest.getChangeSuject());
		writeFileSystem();
	}

	public void delete(TeacherRequest teacherRequest) {
		this.teacherList = init(teacherRequest)
			.delete(teacherList);
		writeFileSystem();
	}

	public List<Teacher> select() {
		return this.teacherList;
	}

	private TeacherCrud init(TeacherRequest teacherRequest) {
		return new TeacherCrudImp(teacherRequest.getName(), teacherRequest.getBirth(),
			teacherRequest.getSubject());
	}

	public void delete(String name) {
		this.teacherList = new TeacherCrudImp().delete(teacherList, name);
	}
}
