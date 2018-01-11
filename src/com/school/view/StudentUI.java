package com.school.view;

import java.util.Scanner;

public class StudentUI {
	Scanner scanner;

	public StudentUI(Scanner scanner) {
		this.scanner = scanner;
	}

	public String inputStudentName() {
		System.out.println("학생 이름>>");
		return scanner.nextLine();
	}

	public String inputStudentBirth() {
		System.out.println("학생 생일>>");
		return scanner.nextLine();
	}

	public String changeStudentName() {
		System.out.println("변경될 학생이름>>");
		return scanner.nextLine();
	}

	public String changeStudentBirth() {
		System.out.println("변경될 생일>>");
		return scanner.nextLine();
	}
}
