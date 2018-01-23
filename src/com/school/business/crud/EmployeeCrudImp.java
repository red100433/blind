package com.school.business.crud;

import java.util.List;

import com.school.custom.EmployeeCrud;
import com.school.models.Type;
import com.school.models.vo.Employee;

/**
 *
 * @author daeyun-jang
 *
 */
public class EmployeeCrudImp implements EmployeeCrud {
	private String tempName;
	private String tempBirth;
	private Employee temp;

	public EmployeeCrudImp(String employeeName, String birth) {
		this.tempName = employeeName;
		this.tempBirth = birth;
		this.temp = new Employee(tempName, tempBirth);
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.EmployeeCrud#insert(java.util.List)
	 */
	@Override
	public List<Employee> insert(List<Employee> list) {
		if (list.size() != Type.LIMIT_PERSON & list.contains(temp) == false) {
			list.add(temp);
		}

		return list;
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.EmployeeCrud#update(java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Employee> update(List<Employee> list, String changeName, String changeBirth) {
		Employee change = new Employee(changeName, changeBirth);
		if (list.contains(temp) & (list.contains(change) == false)) {
			list.remove(temp);
			list.add(change);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.EmployeeCrud#delete(java.util.List)
	 */
	@Override
	public List<Employee> delete(List<Employee> list) {
		list.remove(temp);
		return list;
	}
}