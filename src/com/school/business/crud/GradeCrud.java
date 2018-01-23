package com.school.business.crud;

import java.util.List;

import com.school.exception.InvalidException;
import com.school.models.Grade;
import com.school.models.Student;
import com.school.models.Subject;
import com.school.service.StudentService;
import com.school.service.SubjectService;

import lombok.extern.java.Log;

/**
 *
 * @author daeyun-jang
 *
 */
@Log
public class GradeCrud {

	private String tempStudentName;
	private String tempSubjectName;
	private int tempGrade;
	private Grade temp;
	private List<Subject> subList;
	private List<Student> stuList;

	public GradeCrud(String studentName, String subjectName) {
		this.subList = SubjectService.getInstance().select();
		this.stuList = StudentService.getInstance().select();
		this.tempStudentName = studentName;
		this.tempSubjectName = subjectName;
		this.temp = new Grade(tempStudentName, tempSubjectName);
	}

	public <T> List<T> insert(List<? super T> list, int grade) {
		if (flagSubject(tempSubjectName) & flagStudent(tempStudentName) & list.contains(temp) == false) {
			this.tempGrade = grade;
			this.temp = new Grade(tempStudentName, tempSubjectName, tempGrade);
			list.add((T)temp);
		}
		return (List<T>)list;
	}

	public <T> List<T> update(List<? super T> list, String changeStudentName, String changeSubjectName,
		int changeGrade) {

		if (list.contains(temp) & flagSubject(changeSubjectName) & flagStudent(changeStudentName)) {
			list.remove(temp);
			list.add((T)new Grade(changeStudentName, changeSubjectName, changeGrade));
		} else {
			throw new InvalidException("변경될 학생이나 과목이 등록되지 않았습니다. 먼저 등록해주세요");
		}

		return (List<T>)list;
	}

	public <T> List<T> delete(List<? super T> list) {
		list.remove(temp);
		return (List<T>)list;
	}

	private boolean flagSubject(String subjectName) {
		return subList.stream().anyMatch(s -> s.getSubjectName().equals(subjectName));
	}

	private boolean flagStudent(String studentName) {
		return stuList.stream().anyMatch(s -> s.getStudentName().equals(studentName));
	}
}
