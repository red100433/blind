package com.school.view;

import java.util.Scanner;

public class GradeUI {
	Scanner scanner;

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
}
