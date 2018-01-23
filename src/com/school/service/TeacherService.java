package com.school.service;

import java.util.List;

import com.school.business.crud.TeacherCrudImp;
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
		this.teacherList = new TeacherCrudImp(teacherRequest.getName(), teacherRequest.getBirth(),
			teacherRequest.getSubject())
				.insert(teacherList);
		writeFileSystem();
	}

	public void update(TeacherRequest teacherRequest) {
		this.teacherList = new TeacherCrudImp(teacherRequest.getName(), teacherRequest.getBirth(),
			teacherRequest.getSubject())
				.update(teacherList, teacherRequest.getChangeName(), teacherRequest.getChangeBirth(),
					teacherRequest.getChangeSuject());
		writeFileSystem();
	}

	public void delete(TeacherRequest teacherRequest) {
		this.teacherList = new TeacherCrudImp(teacherRequest.getName(), teacherRequest.getBirth(),
			teacherRequest.getSubject())
				.delete(teacherList);
		writeFileSystem();
	}

	public List<Teacher> select() {
		return this.teacherList;
	}
}
