package com.school.business.crud;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.school.inter.CrudInterface;
import com.school.models.Employee;

/**
 *
 * @author daeyun-jang
 *
 */
public class EmployeeCrud implements CrudInterface {
	private Scanner scanner = new Scanner(System.in);
	private String tempName;
	private String tempBirth;
	private Employee temp;

	public EmployeeCrud() {
		System.out.println("교직원 이름>>");
		this.tempName = scanner.nextLine();
		System.out.println("교직원 생일>>");
		this.tempName = scanner.nextLine();
		this.temp = new Employee(tempName, tempBirth);
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
				System.out.println("변경될 교직원이름>>");
				String setName = scanner.nextLine();
				System.out.println("변경될 생일>>");
				String setBirth = scanner.nextLine();
				list.remove(new Employee(setName, setBirth));
				((Employee)e).setEmployeeName(setName);
				((Employee)e).setBirth((setBirth));
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