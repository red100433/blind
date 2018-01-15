package com.school.view;

import java.util.List;
import java.util.OptionalDouble;
import java.util.Scanner;

import com.school.models.Grade;
import com.school.models.Student;
import com.school.models.Subject;

/**
 *
 * @author daeyun-jang
 *
 */
public class GradeUI {
	private static final int ALL_SELECT = 1;
	private static final int STUDENT_SELECT = 2;
	private static final int STUDENT_AVERAGE_SELECT = 3;
	private static final int ALL_AVERAGE_SELECT = 4;
	private final Scanner scanner;

	public GradeUI(Scanner scanner) {
		this.scanner = scanner;
	}

	public String inputStudentName() {
		System.out.println("학생 이름>>");
		return scanner.nextLine();
	}

	public String inputSubjectName() {
		System.out.println("과목 이름>>");
		return scanner.nextLine();
	}

	public int inputGrade() {
		System.out.println("성적>>");
		int result = Integer.parseInt(scanner.nextLine());

		if (result > 100) {
			return result = 100;
		} else if (result < 0) {
			return result = 0;
		} else {
			return result;
		}
	}

	public String changeStudentName() {
		System.out.println("변경될 학생이름>>");
		return scanner.nextLine();
	}

	public String changeSubjectName() {
		System.out.println("변경될 과목이름>>");
		return scanner.nextLine();
	}

	public int exchangeGrade() {
		System.out.println("변경될 점수>>");
		int result = Integer.parseInt(scanner.nextLine());

		if (result > 100) {
			return result = 100;
		} else if (result < 0) {
			return result = 0;
		} else {
			return result;
		}
	}

	public int selectWay() {
		System.out.println("1.전체조회 2.학생별 조회 3.학생 평균 4.전체 평균>>");
		return Integer.parseInt(scanner.nextLine());
	}

	public String searchName() {
		System.out.println("찾으실 학생의 이름>>");
		return scanner.nextLine();
	}

	public void getGradePrint(List<Grade> gradeList, List<Student> stuList,
		List<Subject> subList) {
		int check = selectWay();

		if (check == ALL_SELECT) {
			gradeList.forEach(System.out::println);
		} else if (check == STUDENT_SELECT) {
			String name = searchName();

			gradeList.stream().filter(s -> s.getStudentName().equals(name)).forEach(System.out::println);
			OptionalDouble average = gradeList.stream()
				.filter(s -> s.getStudentName().equals(name))
				.mapToInt(Grade::getGrade)
				.average();

			if (average.isPresent()) {
				System.out.println(name + "의 평균: " + average.getAsDouble());
			} else {
				System.out.println(name + "의 등록된 성적이 없습니다.");
			}

		} else if (check == STUDENT_AVERAGE_SELECT) {
			for (Student stu : stuList) {
				OptionalDouble average = gradeList.stream()
					.filter(s -> s.getStudentName().equals(stu.getStudentName()))
					.mapToInt(Grade::getGrade)
					.average();

				if (average.isPresent()) {
					System.out.println(stu.getStudentName() + "의 평균: " + average.getAsDouble());
				} else {
					System.out.println(stu.getStudentName() + "의 등록된 성적이 없습니다.");
				}
			}

		} else if (check == ALL_AVERAGE_SELECT) {
			for (Student stu : stuList) {
				OptionalDouble average = gradeList.stream()
					.filter(s -> s.getStudentName().equals(stu.getStudentName()))
					.mapToInt(s -> s.getGrade())
					.average();

				if (average.isPresent()) {
					System.out.println(stu.getStudentName() + "의 평균: " + average.getAsDouble());
				} else {
					System.out.println(stu.getStudentName() + "의 등록된 성적이 없습니다.");
				}
			}

			for (Subject sub : subList) {
				OptionalDouble average = gradeList.stream()
					.filter(s -> s.getSubjectName()
						.equals(sub.getSubjectName()))
					.mapToInt(Grade::getGrade)
					.average();
				if (average.isPresent()) {
					System.out.println(sub.getSubjectName() + "의 평균: " + average.getAsDouble());
				} else {
					System.out.println(sub.getSubjectName() + " 과목의 등록된 성적이 없습니다.");
				}
			}

		}
	}

}
