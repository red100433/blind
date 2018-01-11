package com.school.view;

import java.util.Scanner;

public class TeacherUI {
	Scanner scanner;

	public TeacherUI(Scanner scanner) {
		this.scanner = scanner;
	}

	public String inputTeacherName() {
		System.out.println("선생님 이름>>");
		return scanner.nextLine();
	}

	public String inputTeacherBirth() {
		System.out.println("선생님 생일>>");
		return scanner.nextLine();
	}

	public String inputTeacherSubject() {
		System.out.println("선생님 담당 교과목>>");
		return scanner.nextLine();
	}

	public String changeTeacherName() {
		System.out.println("변경될 선생님이름>>");
		return scanner.nextLine();
	}

	public String changeTeacherBirth() {
		System.out.println("변경될 생일>>");
		return scanner.nextLine();
	}

	public String changeTeacherSubject() {
		System.out.println("변경될 담당 교과목>>");
		return scanner.nextLine();
	}

}
