package com.school.business.crud;

import java.util.List;
import java.util.stream.Collectors;

import com.school.exception.InvalidException;
import com.school.handler.ScannerHandler;
import com.school.inter.CrudInterface;
import com.school.models.Grade;
import com.school.models.Student;
import com.school.models.Subject;
import com.school.service.StudentService;
import com.school.service.SubjectService;
import com.school.view.GradeUI;

/**
 *
 * @author daeyun-jang
 *
 */
public class GradeCrud {

	private final ScannerHandler scanner = ScannerHandler.getInstance();

	private String tempStudentName;
	private String tempSubjectName;
	private int tempGrade;
	private Grade temp;
	private List<Subject> subList;
	private List<Student> stuList;
	private GradeUI gradeUI;

	public GradeCrud() {
		this.gradeUI = new GradeUI(scanner.getScanner());
		this.tempStudentName = gradeUI.inputStudentName();
		this.tempSubjectName = gradeUI.inputSubjectName();
		this.temp = new Grade(tempStudentName, tempSubjectName);
	}

	public GradeCrud(String studentName, String subjectName) {
		this.subList = SubjectService.getInstance().select();
		this.stuList = StudentService.getInstance().select();
		this.tempStudentName = studentName;
		this.tempSubjectName = subjectName;
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
	
	public <T> List<T> insert(List<? super T> list, int grade) {
		boolean stuFlag = false;
		boolean subFlag = false;
		for (Student stu : stuList) {
			if (stu.getStudentName().equals(tempStudentName)) {
				stuFlag = true;
				break;
			}
		}
		for (Subject sub : subList) {
			if (sub.getSubjectName().equals(tempSubjectName)) {
				subFlag = true;
				break;
			}
		}

		if (stuFlag & subFlag) {
			this.tempGrade = grade;
			this.temp = new Grade(tempStudentName, tempSubjectName, tempGrade);
			list.add((T)temp);
		} else {
			throw new InvalidException("등록된 학생이나 과목이  아닙니다. 먼저 등록해주세요"); // exception
		}
		return (List<T>)list.stream().distinct().collect(Collectors.toList());
	}
	
	

	public <T> List<T> update(List<? super T> list) {
		boolean stuFlag = false;
		boolean subFlag = false;

		for (Object e : list) {
			if (e.equals(temp)) {

				String changeStudentName = gradeUI.changeStudentName();
				String changeSubjectName = gradeUI.changeSubjectName();

				for (Student stu : stuList) {
					if (stu.getStudentName().equals(changeStudentName)) {
						stuFlag = true;
					}
				}
				for (Subject sub : subList) {
					if (sub.getSubjectName().equals(changeSubjectName)) {
						subFlag = true;
					}
				}

				if (stuFlag & subFlag) {
					int changeGrade = gradeUI.exchangeGrade();

					list.remove(new Grade(changeStudentName, changeSubjectName));
					((Grade)e).setStudentName(changeStudentName);
					((Grade)e).setSubjectName((changeSubjectName));
					((Grade)e).setGrade(changeGrade);
				} else {
					throw new InvalidException("변경될 학생이나 과목이 등록되지 않았습니다. 먼저 등록해주세요");
				}

			}
		}
		return (List<T>)list;
	}
	
	public <T> List<T> update(List<? super T> list, String changeStudentName, String changeSubjectName, int changeGrade) {
		boolean stuFlag = false;
		boolean subFlag = false;

		for (Object e : list) {
			if (e.equals(temp)) {

				for (Student stu : stuList) {
					if (stu.getStudentName().equals(changeStudentName)) {
						stuFlag = true;
						break;
					}
				}
				for (Subject sub : subList) {
					if (sub.getSubjectName().equals(changeSubjectName)) {
						subFlag = true;
						break;
					}
				}

				if (stuFlag & subFlag) {

					list.remove(new Grade(changeStudentName, changeSubjectName));
					((Grade)e).setStudentName(changeStudentName);
					((Grade)e).setSubjectName((changeSubjectName));
					((Grade)e).setGrade(changeGrade);
				} else {
					throw new InvalidException("변경될 학생이나 과목이 등록되지 않았습니다. 먼저 등록해주세요");
				}

			}
		}
		return (List<T>)list;
	}
	

	public <T> List<T> delete(List<? super T> list) {
		list.remove(temp);
		return (List<T>)list;
	}
}
