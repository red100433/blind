package com.school.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Matchers;

import com.school.business.EmployeeCrudImp;
import com.school.dao.EmployeeDao;
import com.school.models.request.EmployeeRequest;
import com.school.models.vo.Employee;

public class EmployeeServiceTest {
	EmployeeDao dao;

	EmployeeCrudImp crud;

	EmployeeService employeeService;

	List<Employee> list = new ArrayList<>();

	@Before
	public void setUp() {
		list.add(new Employee("", ""));
		list.add(new Employee("jjj", "111"));
		list.add(new Employee("eee", "222"));
		dao = new EmployeeDao();
		crud = new EmployeeCrudImp();
		employeeService = EmployeeService.getInstance();
	}

	@Ignore
	@Test
	public void testGetInstance() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testWriteFileSystem() throws Exception {
		doNothing().when(dao).writeDataList(Matchers.anyListOf(Employee.class));
		dao.writeDataList(list);
		verify(dao, atLeastOnce()).writeDataList(list);
	}

	@Test
	public void testInsert() throws Exception {
		List<Employee> expectList = Arrays.asList(new Employee("", ""),
			new Employee("jjj", "111"), new Employee("eee", "222"), new Employee("fff", "555"));
		EmployeeRequest emp = EmployeeRequest.builder()
			.name("fff").birth("555").changeName("").changeBirth("").build();
		when(crud.insert(Matchers.anyListOf(Employee.class), anyObject())).thenReturn(expectList);

		list = crud.insert(list, emp);
		assertThat(expectList, is(list));

	}

	@Test
	public void testUpdate() throws Exception {
		List<Employee> expectList = Arrays.asList(new Employee("", ""),
			new Employee("jjj", "111"), new Employee("fff", "555"));

		EmployeeRequest emp = EmployeeRequest.builder()
			.name("eee").birth("222").changeName("fff").changeBirth("555").build();

		when(crud.update(Matchers.anyListOf(Employee.class), anyObject()))
			.thenReturn(expectList);

		list = crud.update(list, emp);
		assertThat(expectList, is(list));
	}

	@Test
	public void testDelete() throws Exception {
		List<Employee> expectList = Arrays.asList(new Employee("", ""),
			new Employee("jjj", "111"));

		EmployeeRequest emp = EmployeeRequest.builder()
			.name("eee").birth("222").changeName("").changeBirth("").build();

		when(crud.delete(Matchers.anyListOf(Employee.class), anyObject()))
			.thenReturn(expectList);

		list = crud.delete(list, emp);
		assertThat(expectList, is(list));
	}

	@Test
	public void testSelect() throws Exception {
		List<Employee> expectList = Arrays.asList(new Employee("", ""),
			new Employee("jjj", "111"),
			new Employee("eee", "222"));
		when(dao.readDataList()).thenReturn(expectList);

		assertThat(list, is(dao.readDataList()));
	}

}
