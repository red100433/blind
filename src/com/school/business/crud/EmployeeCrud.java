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
public class EmployeeCrud implements CrudInterface {
	private String tempName;
	private String tempBirth;
	private Employee temp;
	private final ScannerHandler scanner = ScannerHandler.getInstance();
	private final EmployeeUI employeeUI;

	public EmployeeCrud() {
		this.employeeUI = new EmployeeUI(scanner.getScanner());
		this.tempName = employeeUI.inputEmployeeName();
		this.tempBirth = employeeUI.inputEmployeeBirth();
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
				String setName = employeeUI.changeEmployeeName();
				String setBirth = employeeUI.changeEmployeeBirth();
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