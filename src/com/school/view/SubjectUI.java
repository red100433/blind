package com.school.view;

import java.util.Scanner;

public class SubjectUI {
	Scanner scanner;

	public SubjectUI(Scanner scanner) {
		this.scanner = scanner;
	}

	public String inputSubjectName() {
		System.out.println("과목 이름>>");
		return scanner.nextLine();
	}

	public String changeSubjectName() {
		System.out.println("변경될 과목이름>>");
		return scanner.nextLine();
	}

}
