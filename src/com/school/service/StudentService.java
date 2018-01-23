package com.school.service;

import java.util.List;

import com.school.business.crud.StudentCrud;
import com.school.dao.StudentDao;
import com.school.models.Student;
import com.school.models.request.StudentRequest;

public class StudentService {
	private static StudentService t;
	private List<Student> stuList;

	public static StudentService getInstance() {
		synchronized (StudentService.class) {
			if (t == null) {
				t = new StudentService();
			}
		}
		return t;
	}

	private StudentService() {
		this.stuList = new StudentDao().readDataList();
	}

	public void writeFileSystem() {
		new StudentDao().writeDataList(stuList);
	}

	public void insert(StudentRequest stuRequest) {
		this.stuList = new StudentCrud(stuRequest.getName(), stuRequest.getBirth()).insert(stuList);
		writeFileSystem();
	}

	public void update(StudentRequest stuRequest) {
		this.stuList = new StudentCrud(stuRequest.getName(), stuRequest.getBirth())
			.update(stuList, stuRequest.getChangeName(), stuRequest.getChangeBirth());
		writeFileSystem();
	}

	public void delete(StudentRequest stuRequest) {
		this.stuList = new StudentCrud(stuRequest.getName(), stuRequest.getBirth()).delete(stuList);
		writeFileSystem();
	}

	public List<Student> select() {
		return this.stuList;
	}
}