package com.school.service;

import java.util.List;

import com.school.business.crud.StudentCrud;
import com.school.business.crud.TeacherCrud;
import com.school.dao.StudentDao;
import com.school.dao.SubjectDao;
import com.school.dao.TeacherDao;
import com.school.models.Student;
import com.school.models.Teacher;

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
	
	public void insert(String studentName, String birth) {
		this.stuList = new StudentCrud(studentName, birth).insert(stuList);
	}
	
	public void update(String studentName, String birth, String changeName, String changeBirth) {
		this.stuList = new StudentCrud(studentName, birth)
							.update(stuList, changeName, changeBirth);
	}
	
	public void delete(String studentName, String birth) {
		this.stuList = new StudentCrud(studentName, birth).delete(stuList);
	}
	
	public List<Student> select() {
		return this.stuList;
	}
}