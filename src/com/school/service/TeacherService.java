package com.school.service;

import java.util.List;

import com.school.business.crud.SubjectCrud;
import com.school.business.crud.TeacherCrud;
import com.school.dao.TeacherDao;
import com.school.models.Subject;
import com.school.models.Teacher;

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
	
	public void insert(String teacherName, String birth, String subjectName) {
		this.teacherList = new TeacherCrud(teacherName, birth, subjectName).insert(teacherList);
		writeFileSystem();
	}
	
	public void update(String teacherName, String birth, String subjectName, String changeName, String changeBirth, String changeSubjectName) {
		this.teacherList = new TeacherCrud(teacherName, birth, subjectName)
							.update(teacherList, changeName, changeBirth, changeSubjectName);
		writeFileSystem();
	}
	
	public void delete(String teacherName, String birth, String subjectName) {
		this.teacherList = new TeacherCrud(teacherName, birth, subjectName).delete(teacherList);
		writeFileSystem();
	}
	
	public List<Teacher> select() {
		return this.teacherList;
	}
}
