package com.school.business.crud;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.school.inter.CrudInterface;
import com.school.models.Subject;
import com.school.models.Teacher;
import com.school.view.TeacherUI;

/**
 *
 * @author daeyun-jang
 *
 */
public class TeacherCrud implements CrudInterface {
	private Scanner scanner = new Scanner(System.in);
	private String tempName;
	private String tempBirth;
	private String tempSubjectName;
	private Teacher temp;
	private List<Subject> subList;
	private TeacherUI teacherUi = new TeacherUI(scanner);

	public TeacherCrud() {
		this.tempName = teacherUi.inputTeacherName();
		this.tempBirth = teacherUi.inputTeacherBirth();
		this.tempSubjectName = teacherUi.inputTeacherSubject();
		this.temp = new Teacher(tempSubjectName, tempName, tempBirth);
	}

	public TeacherCrud(List<Subject> subList) {
		this.subList = subList;
		this.tempName = teacherUi.inputTeacherName();
		this.tempBirth = teacherUi.inputTeacherBirth();
		this.tempSubjectName = teacherUi.inputTeacherSubject();
		this.temp = new Teacher(tempSubjectName, tempName, tempBirth);
	}

	@Override
	public <T> List<T> insert(List<? super T> list) {
		boolean teacherFlag = false;
		for (Subject sub : subList) {
			if (sub.getSubjectName().equals(tempSubjectName)) {
				teacherFlag = true;
			}
		}
		if (teacherFlag) {
			list.add((T)temp);
		} else {
			System.out.println("등록된 과목이  아닙니다.");
		}

		return (List<T>)list.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public <T> List<T> update(List<? super T> list) {
		boolean teacherFlag = false;

		for (Object e : list) {
			if (e.equals(temp)) {
				String setName = teacherUi.changeTeacherName();
				String setBirth = teacherUi.changeTeacherBirth();
				String setSubjectName = teacherUi.changeTeacherSubject();

				for (Subject sub : subList) {
					if (sub.getSubjectName().equals(setSubjectName)) {
						teacherFlag = true;
					}
				}

				if (teacherFlag) {
					list.remove(new Teacher(setSubjectName, setName, setBirth));
					((Teacher)e).setTeacherName(setName);
					((Teacher)e).setBirth((setBirth));
					((Teacher)e).setSubjectName((setSubjectName));
				} else {
					System.out.println("변경될 교과목이 등록되지 않은 과목입니다. 먼저 교과목을 등록해주세요");
				}
			}
		}
		return (List<T>)list;
	}

	@Override
	public <T> List<T> delete(List<? super T> list) {
		list.remove(temp);
		return (List<T>)list;
	}

}
