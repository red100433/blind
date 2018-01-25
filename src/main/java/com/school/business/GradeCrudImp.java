package com.school.business;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import com.school.custom.GradeCrud;
import com.school.exception.InvalidException;
import com.school.models.Type;
import com.school.models.request.GradeRequest;
import com.school.models.vo.Grade;
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

	public GradeCrudImp() {}

	/* (non-Javadoc)
	 * @see com.school.business.crud.GradeCrud#insert(java.util.List, int)
	 */
	@Override
	public List<Grade> insert(List<Grade> list, GradeRequest gradeRequest) {
		Grade temp = new Grade(gradeRequest.getName(), gradeRequest.getSubject());
		if (flagSubject(gradeRequest.getSubject()) & flagStudent(gradeRequest.getName())
			& list.contains(temp) == false) {
			temp.setGrade(gradeLimit(gradeRequest.getGrade()));
			list.add(temp);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.GradeCrud#update(java.util.List, java.lang.String, java.lang.String, int)
	 */
	@Override
	public List<Grade> update(List<Grade> list, GradeRequest gradeRequest) {
		Grade temp = new Grade(gradeRequest.getName(), gradeRequest.getSubject());
		Grade change = new Grade(gradeRequest.getChangeName(), gradeRequest.getChangeSubject());
		if (list.contains(temp) & (list.contains(change) == false) & flagSubject(gradeRequest.getChangeSubject())
			& flagStudent(gradeRequest.getChangeName())) {
			list.remove(temp);
			change.setGrade(gradeLimit(gradeRequest.getChangeGrade()));
			list.add(change);
		} else {
			throw new InvalidException("변경될 학생이나 과목이 등록되지 않았습니다. 먼저 등록해주세요");
		}

		return list;
	}

	public List<Grade> update(final List<Grade> list, String name, String changeName) {
		if (flagSubject(name) & flagSubject(changeName) == false) {
			list.stream().filter(s -> name.equals(s.getSubjectName())).forEach(s -> s.setSubjectName(changeName));
		} else if (flagStudent(name) & flagStudent(changeName) == false) {
			list.stream().filter(s -> name.equals(s.getStudentName())).forEach(s -> s.setStudentName(changeName));
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.GradeCrud#delete(java.util.List)
	 */
	@Override
	public List<Grade> delete(List<Grade> list, GradeRequest gradeRequest) {
		Grade temp = new Grade(gradeRequest.getName(), gradeRequest.getSubject());
		list.remove(temp);
		return list;
	}

	public List<Grade> delete(List<Grade> list, String name) {
		if (flagSubject(name)) {
			list = list.stream().filter(s -> !name.equals(s.getSubjectName())).collect(Collectors.toList());
		} else if (flagStudent(name)) {
			list = list.stream().filter(s -> !name.equals(s.getStudentName())).collect(Collectors.toList());
		}
		return list;
	}

	private boolean flagSubject(String subjectName) {
		return SubjectService.getInstance().select().stream().anyMatch(s -> s.getSubjectName().equals(subjectName));
	}

	private boolean flagStudent(String studentName) {
		return StudentService.getInstance().select().stream().anyMatch(s -> s.getStudentName().equals(studentName));
	}

	private int gradeLimit(int grade) {
		if (grade > 100) {
			grade = 100;
		} else if (grade < 0) {
			grade = 0;
		}
		return grade;
	}

	@Override
	public List<String> selectOption(List<Grade> list, String selectOption) {
		switch (selectOption) {
			case Type.ALL_SELECT:
				return allSelect(list);

			case Type.ALL_STUDENT_AVERAGE_SELECT:
				List<String> result2 = new ArrayList<>();
				StudentService.getInstance().select().forEach(s -> getStudentAverage(list, s.getStudentName())
					.ifPresent(o -> result2.add(s.getStudentName() + "의 평균:" + o)));
				return result2;

			case Type.ALL_SUBJECT_AVERAGE_SELECT:
				List<String> result3 = new ArrayList<>();
				SubjectService.getInstance().select()
					.forEach(s -> getSubjectAverage(list, s.getSubjectName()).ifPresent(
						o -> result3.add(s.getSubjectName() + "의 평균:" + o)));
				return result3;
		}
		return new ArrayList<>();

	}

	private List<String> allSelect(List<Grade> list) {
		return list.stream()
			.map(o -> o.toString())
			.collect(Collectors.toList());
	}

	private OptionalDouble getStudentAverage(List<Grade> list, String name) {
		return list.stream()
			.filter(s -> s.getStudentName().equals(name))
			.mapToInt(Grade::getGrade)
			.average();
	}

	private OptionalDouble getSubjectAverage(List<Grade> list, String subject) {
		return list.stream()
			.filter(s -> s.getSubjectName().equals(subject))
			.mapToInt(Grade::getGrade)
			.average();
	}

}
