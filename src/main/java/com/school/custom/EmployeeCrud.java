package com.school.custom;

import java.util.List;

import com.school.models.vo.Employee;

public interface EmployeeCrud {

	List<Employee> insert(List<Employee> list);

	List<Employee> update(List<Employee> list, String changeName, String changeBirth);

	List<Employee> delete(List<Employee> list);

}