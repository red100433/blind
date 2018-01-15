package com.school.view;

import java.util.Scanner;

/**
 *
 * @author daeyun-jang
 *
 */
public class EmployeeUI {
	private final Scanner scanner;

	public EmployeeUI(Scanner scanner) {
		this.scanner = scanner;
	}

	public String inputEmployeeName() {
		System.out.println("교직원 이름>>");
		return scanner.nextLine();
	}

	public String inputEmployeeBirth() {
		System.out.println("교직원 생일>>");
		return scanner.nextLine();
	}

	public String changeEmployeeName() {
		System.out.println("변경될 교직원이름>>");
		return scanner.nextLine();
	}

	public String changeEmployeeBirth() {
		System.out.println("변경될 생일>>");
		return scanner.nextLine();
	}

	public void limitEmployee() {
		System.out.println("제한수된 교직원 수를 넘었습니다. 삭제해 주세요");
	}
}
