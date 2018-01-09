package com.school.business.crud;

import java.util.List;
import java.util.Scanner;

import com.school.inter.CrudInterface;
import com.school.models.Teacher;

/**
 *
 * @author daeyun-jang
 *
 */
public class TeacherCrud implements CrudInterface {
	private Scanner scanner = new Scanner(System.in);
	private String tempName;
	private String tempBirth;
	private String tempSubjectName;
	private Teacher temp;

	public TeacherCrud() {
		System.out.println("선생님 이름>>");
		this.tempName = scanner.nextLine();
		System.out.println("선생님 생일>>");
		this.tempName = scanner.nextLine();
		System.out.println("선생님 교과목>>");
		this.tempSubjectName = scanner.nextLine();
		this.temp = new Teacher(tempSubjectName, tempName, tempBirth);
	}

	@Override
	public <T> List<T> insert(List<? super T> list) {
		for (Object e : list) {
			if (e.equals(temp)) {
				return (List<T>)list;
			}
			if (e == list.get(list.size() - 1)) {
				list.add((T)temp);
			}
		}
		return (List<T>)list;
	}

	@Override
	public <T> List<T> update(List<? super T> list) {
		for (Object e : list) {
			if (e.equals(temp)) {
				System.out.println("수정할 이름>>");
				String setName = scanner.nextLine();
				System.out.println("수정할 생일>>");
				String setBirth = scanner.nextLine();
				System.out.println("수정할 교과목>>");
				String setSubjectName = scanner.nextLine();
				((Teacher)e).setTeacherName(setName);
				((Teacher)e).setBirth((setBirth));
				((Teacher)e).setSubjectName((setSubjectName));
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
