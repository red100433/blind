package com.school.service;

import java.util.List;

import com.school.business.crud.SubjectCrud;
import com.school.dao.SubjectDao;
import com.school.dao.TeacherDao;
import com.school.models.Subject;

public class SubjectService {
	private static SubjectService t;
	private List<Subject> subList;
	public static SubjectService getInstance() {
		synchronized (SubjectService.class) {
			if (t == null) {
				t = new SubjectService();
			}
		}
		return t;
	}
	private SubjectService() { 
		this.subList = new SubjectDao().readDataList();
	}
	
	public void writeFileSystem() {
		new SubjectDao().writeDataList(subList);
	}
	
	public void insert(String subName) {
		this.subList = new SubjectCrud(subName).insert(subList);
		writeFileSystem();
	}
	
	public void update(String subName, String changeName) {
		this.subList = new SubjectCrud(subName).update(subList, changeName);
		writeFileSystem();
	}
	
	public void delete(String subName) {
		this.subList = new SubjectCrud(subName).delete(subList);
		writeFileSystem();
	}
	
	public List<Subject> select() {
		return this.subList;
	}
}
