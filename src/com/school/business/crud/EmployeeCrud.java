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

	public <T> List<T> insert(List<? super T> list) {
		if (list.size() != Type.LIMIT_PERSON & list.contains(temp) == false) {
			list.add((T)temp);
		}

		return (List<T>)list;
	}

	public <T> List<T> update(List<? super T> list, String changeName, String changeBirth) {

		if (list.contains(temp) & (list.contains(new Employee(changeName, changeBirth)) == false)) {
			list.remove(temp);
			list.add((T)new Employee(changeName, changeBirth));
			return (List<T>)list;
		}

		return (List<T>)list;
	}

	public <T> List<T> delete(List<? super T> list) {
		list.remove(temp);
		return (List<T>)list;
	}
}