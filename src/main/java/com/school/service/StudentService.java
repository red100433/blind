package com.school.service;

import java.util.List;

import com.school.business.crud.StudentCrudImp;
import com.school.custom.StudentCrud;
import com.school.dao.StudentDao;
import com.school.models.request.StudentRequest;
import com.school.models.vo.Student;

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
		this.stuList = init(stuRequest).insert(stuList);
		writeFileSystem();
	}

	public void update(StudentRequest stuRequest) {
		this.stuList = init(stuRequest)
			.update(stuList, stuRequest.getChangeName(), stuRequest.getChangeBirth());
		writeFileSystem();
	}

	public void delete(StudentRequest stuRequest) {
		this.stuList = init(stuRequest).delete(stuList);
		writeFileSystem();
	}

	public List<Student> select() {
		return this.stuList;
	}

	private StudentCrud init(StudentRequest stuRequest) {
		return new StudentCrudImp(stuRequest.getName(), stuRequest.getBirth());
	}
}