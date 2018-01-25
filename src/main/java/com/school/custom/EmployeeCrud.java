package com.school.custom;

import java.util.List;

import com.school.models.request.EmployeeRequest;
import com.school.models.vo.Employee;

public interface EmployeeCrud {

	List<Employee> insert(List<Employee> list, EmployeeRequest empRequest);

	List<Employee> update(List<Employee> list, EmployeeRequest empRequest);

	List<Employee> delete(List<Employee> list, EmployeeRequest empRequest);

}