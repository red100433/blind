package com.school.service;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.school.business.EmployeeCrudImp;
import com.school.dao.EmployeeDao;
import com.school.models.vo.Employee;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
	@Mock
	EmployeeDao dao;

	@Mock
	EmployeeCrudImp crud;

	@InjectMocks
	EmployeeService employeeService;

	List<Employee> empList;

	//	@Before
	//	public void setUp() {
	//		Employee expectedEmp = new Employee("jang", "111");
	//		when(dao.readDataList()).thenReturn(anyList());
	//		empList = dao.readDataList();
	//		assertEquals
	//	}
	//
	//	@After
	//	public void writeFileSystem() {
	//		doNothing().when(dao).writeDataList(anyList());
	//		dao.writeDataList(empList);
	//		verify(dao, atLeast(1));
	//	}

	@Test
	public void insert() {
		Employee expectedEmp = new Employee("jang", "111");
		empList.add(expectedEmp);
		//		when(crud.insert(anyList())).thenReturn(empList);
		//		this.empList = crud.insert(empList);
		verify(crud, times(1));
	}

}
