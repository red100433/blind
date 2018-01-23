package com.school.business.crud;

import java.util.List;

import com.school.models.Employee;
import com.school.models.Type;

/**
 *
 * @author daeyun-jang
 *
 */
public class EmployeeCrud {
	private String tempName;
	private String tempBirth;
	private Employee temp;

	public EmployeeCrud(String employeeName, String birth) {
		this.tempName = employeeName;
		this.tempBirth = birth;
		this.temp = new Employee(tempName, tempBirth);
	}

	public List<Employee> insert(List<Employee> list) {
		if (list.size() != Type.LIMIT_PERSON & list.contains(temp) == false) {
			list.add(temp);
		}

		return list;
	}

	public List<Employee> update(List<Employee> list, String changeName, String changeBirth) {

		if (list.contains(temp) & (list.contains(new Employee(changeName, changeBirth)) == false)) {
			list.remove(temp);
			list.add(new Employee(changeName, changeBirth));
		}
		return list;
	}

	public List<Employee> delete(List<Employee> list) {
		list.remove(temp);
		return list;
	}
}