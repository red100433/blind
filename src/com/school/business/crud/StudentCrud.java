package com.school.business.crud;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.school.inter.CrudInterface;
import com.school.models.Student;

/**
 *
 * @author daeyun-jang
 *
 */
public class StudentCrud implements CrudInterface {
	private Scanner scanner = new Scanner(System.in);
	private String tempName;
	private String tempBirth;
	private Student temp;

	public StudentCrud() {
		System.out.println("학생 이름>>");
		this.tempName = scanner.nextLine();
		System.out.println("학생 생일>>");
		this.tempBirth = scanner.nextLine();
		this.temp = new Student(tempName, tempBirth);
	}

	@Override
	public <T> List<T> insert(List<? super T> list) {
		list.add((T)temp);

		return (List<T>)list.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public <T> List<T> update(List<? super T> list) {
		for (Object e : list) {
			if (e.equals(temp)) {
				System.out.println("변경될 학생이름>>");
				String setName = scanner.nextLine();
				System.out.println("변경될 생일>>");
				String setBirth = scanner.nextLine();
				list.remove(new Student(setName, setBirth));
				((Student)e).setStudentName(setName);
				((Student)e).setBirth((setBirth));
				return (List<T>)list;
			}
		}
		return (List<T>)list;
	}

	@Override
	public <T> List<T> delete(List<? super T> list) {
		list.remove(temp);
		return (List<T>)list;
	}
}
