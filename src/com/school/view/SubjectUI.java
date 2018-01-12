package com.school.view;

import java.util.Scanner;

/**
 *
 * @author daeyun-jang
 *
 */
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

	public void limitSubject() {
		System.out.println("제한수된 과목수를 넘었습니다. 삭제해 주세요");
	}
}
