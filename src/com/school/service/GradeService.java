package com.school.service;

import java.util.List;

import com.school.business.crud.EmployeeCrud;
import com.school.business.crud.GradeCrud;
import com.school.dao.EmployeeDao;
import com.school.dao.GradeDao;
import com.school.dao.SubjectDao;
import com.school.models.Employee;
import com.school.models.Grade;

public class GradeService {
	private static GradeService t;
	private List<Grade> gradeList;
	public static GradeService getInstance() {
		synchronized (GradeService.class) {
			if (t == null) {
				t = new GradeService();
			}
		}
		return t;
	}
	private GradeService() {
		this.gradeList = new GradeDao().readDataList(); 
	}
	
	public void writeFileSystem() {
		new GradeDao().writeDataList(gradeList);
	}
	
	public void insert(String studentName, String subjectName, int grade) {
		this.gradeList = new GradeCrud(studentName, subjectName).insert(gradeList, grade);
		writeFileSystem();
	}
	
	public void update(String studentName, String subjectName, String changeStudentName, String changeSubjectName, int changeGrade) {
		this.gradeList = new GradeCrud(studentName, subjectName)
							.update(gradeList, changeStudentName, changeSubjectName, changeGrade);
		writeFileSystem();
	}
	
	public void delete(String studentName, String subjectName) {
		this.gradeList = new GradeCrud(studentName, subjectName).delete(gradeList);
		writeFileSystem();
	}
	
	public List<Grade> select() {
		return this.gradeList;
	}
}
