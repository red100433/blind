package com.school.view;

import java.util.Scanner;

/**
 *
 * @author daeyun-jang
 *
 */
public class ServiceUI {
	private final Scanner scanner;

	public ServiceUI(Scanner scanner) {
		this.scanner = scanner;
	}

	public String selectManageMent() {
		System.out.println("1.인원관리  2.과목관리  3.성적관리  99.종료>>");
		return scanner.nextLine();
	}

	public String selectCrud() {
		System.out.println("1.입력   2.수정  3.삭제  4.조회>>");
		return scanner.nextLine();
	}

	public int selectPerson() {
		System.out.println("1.학생 2.교직원 3.선생님>>");
		return Integer.parseInt(scanner.nextLine());
	}

}
