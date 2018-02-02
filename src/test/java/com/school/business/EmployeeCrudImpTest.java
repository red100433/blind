package com.school.business;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.school.models.vo.Employee;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeCrudImpTest {

	//	@Mock
	//	EmployeeCrud mock;

	//	@InjectMocks
	//	EmployeeCrudImp mockEmp;

	@Test
	public void insertTest() {

		List<Employee> insertList = new ArrayList<>();
		//		insertList.add(new Employee("", ""));
		//		EmployeeRequest emp = EmployeeRequest.builder()
		//			.name("jjj").birth("111").changeName("").changeBirth("").build();

		// 기대값(리턴값)
		//		List<Employee> exfInsertList = Arrays.asList(new Employee("", ""), new Employee("jjj", "111"));
		//함수 호출됫을때 리턴하는 값을 지정
		//		when(mock.insert(Matchers.anyListOf(Employee.class), anyObject())).thenReturn(exfInsertList);

		//호출
		//		insertList = mockEmp.insert(insertList, emp);

		// 기대값이랑, (assert
		//		assertThat(exfInsertList, is(insertList));
	}

	@Test
	public void updateTest() {
		List<Employee> updateList = new ArrayList<>();
		//		updateList.add(new Employee("", ""));
		//		List<Employee> exfInsertList = Arrays.asList(new Employee("jjj", "111"));

		//		when(mock.update(Matchers.anyListOf(Employee.class), anyObject())).thenReturn(exfInsertList);

		//		EmployeeRequest emp = EmployeeRequest.builder()
		//			.name("").birth("").changeName("jjj").changeBirth("111").build();

		//		updateList = mockEmp.update(updateList, emp);

		//		assertThat(exfInsertList, is(updateList));
	}

	@Test
	public void deleteTest() {
		List<Employee> deleteList = new ArrayList<>();
		//		deleteList.add(new Employee("jjj", "111"));
		List<Employee> exfInsertList = Collections.emptyList();

		//		when(mock.delete(Matchers.anyListOf(Employee.class), anyObject())).thenReturn(exfInsertList);

		//		EmployeeRequest emp = EmployeeRequest.builder()
		//			.name("jjj").birth("111").changeName("").changeBirth("").build();

		//		deleteList = mockEmp.delete(deleteList, emp);

		assertThat(exfInsertList, is(deleteList));
	}
}
