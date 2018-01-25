package com.school.service;

import java.util.List;

import com.school.business.StudentCrudImp;
import com.school.custom.StudentCrud;
import com.school.dao.StudentDao;
import com.school.models.request.StudentRequest;
import com.school.models.vo.Student;

public class StudentService {
	private static StudentService t;

	public static StudentService getInstance() {
		synchronized (StudentService.class) {
			if (t == null) {
				t = new StudentService();
			}
		}
		return t;
	}

	private StudentService() {}

	public void writeFileSystem(List<Student> stuList) {
		new StudentDao().writeDataList(stuList);
	}

	public void insert(StudentRequest stuRequest) {
		List<Student> stuList = select();
		stuList = init().insert(stuList, stuRequest);
		writeFileSystem(stuList);
	}

	public void update(StudentRequest stuRequest) {
		List<Student> stuList = select();
		stuList = init()
			.update(stuList, stuRequest);
		writeFileSystem(stuList);
	}

	public void delete(StudentRequest stuRequest) {
		List<Student> stuList = select();
		stuList = init().delete(stuList, stuRequest);
		writeFileSystem(stuList);
	}

	public List<Student> select() {
		return new StudentDao().readDataList();
	}

	private StudentCrud init() {
		return new StudentCrudImp();
	}
}