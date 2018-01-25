package com.school.business;

import java.util.List;

import com.school.custom.EmployeeCrud;
import com.school.models.Type;
import com.school.models.request.EmployeeRequest;
import com.school.models.vo.Employee;

/**
 *
 * @author daeyun-jang
 *
 */
public class EmployeeCrudImp implements EmployeeCrud {

	/* (non-Javadoc)
	 * @see com.school.business.crud.EmployeeCrud#insert(java.util.List)
	 */
	@Override
	public List<Employee> insert(List<Employee> list, EmployeeRequest empRequest) {
		Employee temp = new Employee(empRequest.getName(), empRequest.getBirth());
		if (list.size() != Type.LIMIT_PERSON & list.contains(temp) == false) {
			list.add(temp);
		}

		return list;
	}

	/* (non-Javadoc)
	 * @see com.school.business.crud.EmployeeCrud#update(java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Employee> update(List<Employee> list, EmployeeRequest empRequest) {
		Employee temp = new Employee(empRequest.getName(), empRequest.getBirth());
		Employee change = new Employee(empRequest.getChangeName(), empRequest.getChangeBirth());
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
	public List<Employee> delete(List<Employee> list, EmployeeRequest empRequest) {
		Employee temp = new Employee(empRequest.getName(), empRequest.getBirth());
		list.remove(temp);
		return list;
	}

}