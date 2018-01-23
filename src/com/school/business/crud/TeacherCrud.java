package com.school.business.crud;

import java.util.List;

import com.school.exception.InvalidException;
import com.school.models.Type;
import com.school.models.vo.Subject;
import com.school.models.vo.Teacher;
import com.school.service.SubjectService;

import lombok.extern.java.Log;

/**
 *
 * @author daeyun-jang
 *
 */

@Log
public class TeacherCrud {
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

	public List<Teacher> insert(List<Teacher> list) {

		if (flagSubject(tempSubjectName) & list.size() != Type.LIMIT_PERSON & list.contains(temp) == false) {
			list.add(temp);
		}

		return list;
	}

	public List<Teacher> update(List<Teacher> list, String changeName, String changeBirth, String changeSubject) {
		Teacher change = new Teacher(changeSubject, changeName, changeBirth);
		if (list.contains(temp) & flagSubject(changeSubject)
			& (list.contains(change) == false)) {
			list.remove(temp);
			list.add(change);
		} else {
			throw new InvalidException("변경될 교과목이 등록되지 않은 과목입니다. 먼저 교과목을 등록해주세요");
		}

		return list;
	}

	public List<Teacher> delete(List<Teacher> list) {
		list.remove(temp);
		return list;
	}

	private boolean flagSubject(String subjectName) {
		return subList.stream().anyMatch(s -> s.getSubjectName().equals(subjectName));
	}
}
