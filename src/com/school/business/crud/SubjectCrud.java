package com.school.business.crud;

import java.util.List;
import java.util.Scanner;

import com.school.inter.CrudInterface;
import com.school.models.Subject;

/**
 *
 * @author daeyun-jang
 *
 */
public class SubjectCrud implements CrudInterface {
	private Scanner scanner = new Scanner(System.in);
	private String tempName;
	private Subject temp;

	public SubjectCrud() {
		System.out.println("과목 이름>>");
		this.tempName = scanner.nextLine();
		this.temp = new Subject(tempName);
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
				list.remove(new Subject(setName));
				((Subject)e).setSubjectName(setName);
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
