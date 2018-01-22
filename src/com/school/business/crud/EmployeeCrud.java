package com.school.business.crud;

import java.util.List;
import java.util.stream.Collectors;

import com.school.handler.ScannerHandler;
import com.school.inter.CrudInterface;
import com.school.models.Employee;
import com.school.view.EmployeeUI;

/**
 *
 * @author daeyun-jang
 *
 */
public class EmployeeCrud {
	private static final int LIMIT_EMPLOYEE = 1000;
	private final ScannerHandler scanner = ScannerHandler.getInstance();
	private String tempName;
	private String tempBirth;
	private Employee temp;
	private EmployeeUI employeeUI;

	public EmployeeCrud() {
		this.employeeUI = new EmployeeUI(scanner.getScanner());
		this.tempName = employeeUI.inputEmployeeName();
		this.tempBirth = employeeUI.inputEmployeeBirth();
		this.temp = new Employee(tempName, tempBirth);
	}

	public EmployeeCrud(String employeeName, String birth) {
		this.tempName = employeeName;
		this.tempBirth = birth;
		this.temp = new Employee(tempName, tempBirth);
	}
	
	public <T> List<T> insert(List<? super T> list) {
		if (list.size() != LIMIT_EMPLOYEE) {
			list.add((T)temp);
		} else {
			this.employeeUI.limitEmployee();
		}

		return (List<T>)list.stream().distinct().collect(Collectors.toList());
	}

	public <T> List<T> update(List<? super T> list) {
		for (Object e : list) {
			if (e.equals(temp)) {
				String changeName = employeeUI.changeEmployeeName();
				String changeBirth = employeeUI.changeEmployeeBirth();
				list.remove(new Employee(changeName, changeBirth));
				((Employee)e).setEmployeeName(changeName);
				((Employee)e).setBirth((changeBirth));
				return (List<T>)list;
			}
		}
		return (List<T>)list;
	}

	public <T> List<T> update(List<? super T> list, String changeName, String changeBirth) {
		for (Object e : list) {
			if (e.equals(temp)) {
				list.remove(new Employee(changeName, changeBirth));
				((Employee)e).setEmployeeName(changeName);
				((Employee)e).setBirth((changeBirth));
				return (List<T>)list;
			}
		}
		return (List<T>)list;
	}
	
	public <T> List<T> delete(List<? super T> list) {
		list.remove(temp);
		return (List<T>)list;
	}
}