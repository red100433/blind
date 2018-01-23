package com.school.business.crud;

import java.util.List;

import com.school.custom.GradeCrud;
import com.school.exception.InvalidException;
import com.school.models.vo.Grade;
import com.school.models.vo.Student;
import com.school.models.vo.Subject;
import com.school.service.StudentService;
import com.school.service.SubjectService;

import lombok.extern.java.Log;

/**
 *
 * @author daeyun-jang
 *
 */
@Log
public class GradeCrudImp implements GradeCrud {

	private String tempStudentName;
	private String tempSubjectName;
	private int tempGrade;
	private Grade temp;
	private List<Subject> subList;
	private List<Student> stuList;

	public GradeCrudImp(String studentName, String subjectName) {
		this.subList = SubjectService.getInstance().select();
		this.stuList = StudentService.getInstance().select();
		this.tempStudentName = studentName;
		this.tempSubjectName = subjectName;
		this.temp = new Grade(tempStudentName, tempSubjectName);
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.GradeCrud#insert(java.util.List, int)
	 */
	@Override
	public List<Grade> insert(List<Grade> list, int grade) {
		if (flagSubject(tempSubjectName) & flagStudent(tempStudentName) & list.contains(temp) == false) {
			this.tempGrade = gradeLimit(grade);
			this.temp = new Grade(tempStudentName, tempSubjectName, tempGrade);
			list.add(temp);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.GradeCrud#update(java.util.List, java.lang.String, java.lang.String, int)
	 */
	@Override
	public List<Grade> update(List<Grade> list, String changeStudentName, String changeSubjectName,
		int changeGrade) {
		Grade change = new Grade(changeStudentName, changeSubjectName);
		if (list.contains(temp) & (list.contains(change) == false) & flagSubject(changeSubjectName)
			& flagStudent(changeStudentName)) {
			list.remove(temp);
			change.setGrade(gradeLimit(changeGrade));
			list.add(change);
		} else {
			throw new InvalidException("변경될 학생이나 과목이 등록되지 않았습니다. 먼저 등록해주세요");
		}

		return list;
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.GradeCrud#delete(java.util.List)
	 */
	@Override
	public List<Grade> delete(List<Grade> list) {
		list.remove(temp);
		return list;
	}

	private boolean flagSubject(String subjectName) {
		return subList.stream().anyMatch(s -> s.getSubjectName().equals(subjectName));
	}

	private boolean flagStudent(String studentName) {
		return stuList.stream().anyMatch(s -> s.getStudentName().equals(studentName));
	}

	private int gradeLimit(int grade) {
		if (grade > 100) {
			grade = 100;
		} else if (grade < 0) {
			grade = 0;
		}
		return grade;
	}

}
