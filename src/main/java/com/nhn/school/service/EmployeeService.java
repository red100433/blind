package com.nhn.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhn.school.dao.EmployeeDao;
import com.nhn.school.exception.PeopleCapacity;
import com.nhn.school.model.Employee;
import com.nhn.school.model.Type;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao dao;

	public void save(Employee employee) {
		if (dao.count() < Type.PERSON_SIZE) {
			dao.save(employee);
		} else {
			new PeopleCapacity();
		}
	}

	public void delete(int id) {
		dao.delete(id);
	}

	public List<Employee> findAll() {
		return dao.findAll();
	}

}
