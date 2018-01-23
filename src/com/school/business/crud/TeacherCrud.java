package com.school.business.crud;

import java.util.List;

import com.school.exception.InvalidException;
import com.school.models.Subject;
import com.school.models.Teacher;
import com.school.service.SubjectService;

import lombok.extern.java.Log;

/**
 *
 * @author daeyun-jang
 *
 */

@Log
public class TeacherCrud {
	private static final int LIMIT_TEACHER = 1000;
	private String tempName;
	private String tempBirth;
	private String tempSubjectName;
	private Teacher temp;
	private List<Subject> subList;

	public TeacherCrud(String teacherName, String birth, String subName) {
		this.tempName = teacherName;
		this.tempBirth = birth;
		this.tempSubjectName = subName;
		this.subList = SubjectService.getInstance().select();
		this.temp = new Teacher(tempSubjectName, tempName, tempBirth);
	}

	public <T> List<T> insert(List<? super T> list) {

		if (flagSubject(tempSubjectName) & list.size() != LIMIT_TEACHER & list.contains(temp) == false) {
			list.add((T)temp);
		}

		return (List<T>)list;
	}

	public <T> List<T> update(List<? super T> list, String changeName, String changeBirth, String changeSubject) {

		if (list.contains(temp) & flagSubject(changeSubject)
			& (list.contains(new Teacher(changeSubject, changeName, changeBirth)) == false)) {
			list.remove(temp);
			list.add((T)new Teacher(changeSubject, changeName, changeBirth));
		} else {
			throw new InvalidException("변경될 교과목이 등록되지 않은 과목입니다. 먼저 교과목을 등록해주세요");
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
}
