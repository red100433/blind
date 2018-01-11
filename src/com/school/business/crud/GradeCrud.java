package com.school.business.crud;

import java.util.List;
import java.util.stream.Collectors;

import com.school.exception.InvalidException;
import com.school.handler.ScannerHandler;
import com.school.inter.CrudInterface;
import com.school.models.Grade;
import com.school.models.Student;
import com.school.models.Subject;
import com.school.view.GradeUI;

/**
 *
 * @author daeyun-jang
 *
 */
public class GradeCrud implements CrudInterface {
	private static final int ALL_SELECT = 1;
	private static final int STUDENT_SELECT = 2;
	private static final int STUDENT_AVERAGE_SELECT = 3;
	private static final int ALL_AVERAGE_SELECT = 4;
	private final ScannerHandler scanner = ScannerHandler.getInstance();

	private String tempStudentName;
	private String tempSubjectName;
	private int tempGrade;
	private Grade temp;
	private List<Subject> subList;
	private List<Student> stuList;
	private final GradeUI gradeUI;

	public GradeCrud() {
		this.gradeUI = new GradeUI(scanner.getScanner());
		this.tempStudentName = gradeUI.inputStudentName();
		this.tempSubjectName = gradeUI.inputSubjectName();
		this.temp = new Grade(tempStudentName, tempSubjectName);
	}

	public GradeCrud(List<Subject> subList, List<Student> stuList) {
		this.subList = subList;
		this.stuList = stuList;
		this.gradeUI = new GradeUI(scanner.getScanner());
		this.tempStudentName = gradeUI.inputStudentName();
		this.tempSubjectName = gradeUI.inputSubjectName();
		this.temp = new Grade(tempStudentName, tempSubjectName);
	}

	@Override
	public <T> List<T> insert(List<? super T> list) {
		boolean stuFlag = false;
		boolean subFlag = false;
		for (Student stu : stuList) {
			if (stu.getStudentName().equals(tempStudentName)) {
				stuFlag = true;
			}
		}
		for (Subject sub : subList) {
			if (sub.getSubjectName().equals(tempSubjectName)) {
				subFlag = true;
			}
		}

		if (stuFlag & subFlag) {
			this.tempGrade = gradeUI.inputGrade();
			this.temp = new Grade(tempStudentName, tempSubjectName, tempGrade);
			list.add((T)temp);
		} else {
			throw new InvalidException("등록된 학생이나 과목이  아닙니다. 먼저 등록해주세요"); // exception
		}
		return (List<T>)list.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public <T> List<T> update(List<? super T> list) {
		boolean stuFlag = false;
		boolean subFlag = false;

		for (Object e : list) {
			if (e.equals(temp)) {

				String setStudentName = gradeUI.changeStudentName();
				String setSubjectName = gradeUI.changeSubjectName();

				for (Student stu : stuList) {
					if (stu.getStudentName().equals(setStudentName)) {
						stuFlag = true;
					}
				}
				for (Subject sub : subList) {
					if (sub.getSubjectName().equals(setSubjectName)) {
						subFlag = true;
					}
				}

				if (stuFlag & subFlag) {
					int setGrade = gradeUI.exchangeGrade();

					list.remove(new Grade(setStudentName, setSubjectName));
					((Grade)e).setStudentName(setStudentName);
					((Grade)e).setSubjectName((setSubjectName));
					((Grade)e).setGrade(setGrade);
				} else {
					throw new InvalidException("변경될 학생이나 과목이 등록되지 않았습니다. 먼저 등록해주세요");
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

	public void read(List<Grade> list, List<Subject> subList, List<Student> stuList) {
		int check = gradeUI.selectWay();

		if (check == ALL_SELECT) {
			list.forEach(System.out::println);
		} else if (check == STUDENT_SELECT) {
			String name = gradeUI.searchName();

			list.stream().filter(s -> s.getStudentName().equals(name)).forEach(System.out::println);
			double average = list.stream().filter(s -> s.getStudentName().equals(name))
				.mapToInt(Grade::getGrade).average().getAsDouble();
			System.out.println(name + "의 평균: " + average);
		} else if (check == STUDENT_AVERAGE_SELECT) {
			for (Student stu : stuList) {
				double average = list.stream().filter(s -> s.getStudentName().equals(stu.getStudentName()))
					.mapToInt(Grade::getGrade).average().getAsDouble();
				System.out.println(stu.getStudentName() + "의 평균: " + average);
			}
		} else if (check == ALL_AVERAGE_SELECT) {
			for (Student stu : stuList) {
				double average = list.stream().filter(s -> s.getStudentName().equals(stu.getStudentName()))
					.mapToInt(Grade::getGrade).average().getAsDouble();
				System.out.println(stu.getStudentName() + "의 평균: " + average);
			}

			for (Subject sub : subList) {
				double average = list.stream().filter(s -> s.getSubjectName().equals(sub.getSubjectName()))
					.mapToInt(Grade::getGrade).average().getAsDouble();
				System.out.println(sub.getSubjectName() + "의 평균: " + average);
			}
		}
	}

}
