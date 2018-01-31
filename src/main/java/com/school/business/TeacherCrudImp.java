package com.school.business;

import java.util.List;

import com.school.custom.TeacherCrud;
import com.school.models.request.TeacherRequest;
import com.school.models.vo.Teacher;
import com.school.service.SubjectService;

import lombok.extern.java.Log;

/**
 *
 * @author daeyun-jang
 *
 */

@Log
public class TeacherCrudImp implements TeacherCrud {

	public TeacherCrudImp() {}

	/* (non-Javadoc)
	 * @see com.school.business.crud.TeacherCrud#insert(java.util.List)
	 */
	@Override
	public List<Teacher> insert(List<Teacher> list, TeacherRequest teacherRequest) {
		//		Teacher temp = new Teacher(teacherRequest.getName(), teacherRequest.getSubject(), teacherRequest.getBirth());
		//		if (flagSubject(teacherRequest.getSubject())
		//			& list.size() != Type.LIMIT_PERSON
		//			& list.contains(temp) == false) {
		//			list.add(temp);
		//		}

		return list;
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.TeacherCrud#update(java.util.List, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Teacher> update(List<Teacher> list, TeacherRequest teacherRequest) {
		//		Teacher temp = new Teacher(teacherRequest.getName(), teacherRequest.getSubject(), teacherRequest.getBirth());
		//		Teacher change = new Teacher(teacherRequest.getChangeName(), teacherRequest.getChangeSuject(),
		//			teacherRequest.getChangeBirth());
		//		if (list.contains(temp) & flagSubject(teacherRequest.getChangeSuject())
		//			& (list.contains(change) == false)) {
		//			list.remove(temp);
		//			list.add(change);
		//		} else {
		//			throw new InvalidException("변경될 교과목이 등록되지 않은 과목입니다. 먼저 교과목을 등록해주세요");
		//		}

		return list;
	}

	@Override
	public List<Teacher> update(List<Teacher> list, String name, String changeName) {
		//		if (flagSubject(name) & flagSubject(changeName) == false) {
		//			list.stream().filter(s -> name.equals(s.getSubjectName())).forEach(s -> s.setSubjectName(changeName));
		//		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.TeacherCrud#delete(java.util.List)
	 */
	@Override
	public List<Teacher> delete(List<Teacher> list, TeacherRequest teacherRequest) {
		//		Teacher temp = new Teacher(teacherRequest.getName(), teacherRequest.getSubject(), teacherRequest.getBirth());
		//		list.remove(temp);
		return list;
	}

	public List<Teacher> delete(List<Teacher> list, String name) {
		//		list.stream().filter(s -> name.equals(s.getSubjectName())).forEach(s -> s.setSubjectName(""));
		return list;
	}

	private boolean flagSubject(String subjectName) {
		return SubjectService.getInstance().getAllSubjects().stream()
			.anyMatch(s -> s.getSubjectName().equals(subjectName));
	}

}
