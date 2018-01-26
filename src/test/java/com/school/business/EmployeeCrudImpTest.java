package com.school.business;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.runners.MockitoJUnitRunner;

import com.school.models.request.EmployeeRequest;
import com.school.models.vo.Employee;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeCrudImpTest {

	@InjectMocks
	EmployeeCrudImp mockEmp;

	@Test
	public void insertTest() {

		List<Employee> insertList = new ArrayList<>();
		insertList.add(new Employee("", ""));
		EmployeeRequest emp = EmployeeRequest.builder()
			.name("jjj").birth("111").changeName("").changeBirth("").build();

		// 기대값(리턴값)
		List<Employee> exfInsertList = Arrays.asList(new Employee("", ""), new Employee("jjj", "111"));
		//함수 호출됫을때 리턴하는 값을 지정
		when(mockEmp.insert(Matchers.anyListOf(Employee.class), anyObject())).thenReturn(exfInsertList);

		//호출
		insertList = mockEmp.insert(insertList, emp);

		// 기대값이랑, (assert
		assertThat(exfInsertList, is(insertList));
	}
	//	@Test
	//	public void insertTest() {
	//
	//		List<Employee> insertList = new ArrayList<>();
	//		insertList.add(new Employee("", ""));
	//
	//		// 기대값(리턴값)
	//		List<Employee> exfInsertList = Arrays.asList(new Employee("", ""), new Employee("jjj", "111"));
	//		//함수 호출됫을때 리턴하는 값을 지정
	//		when(mockEmp.insert(Matchers.anyListOf(Employee.class), anyObject())).thenReturn(exfInsertList);
	//
	//		insertList.add(new Employee("jjj", "111"));
	//
	//		EmployeeRequest emp = EmployeeRequest.builder()
	//			.name("jjj").birth("111").changeName("").changeBirth("").build();
	//
	//		//호출
	//		insertList = mockEmp.insert(insertList, emp);
	//
	//		// 기대값이랑, (assert
	//		assertThat(exfInsertList, is(insertList));
	//	}

	@Test
	public void updateTest() {
		List<Employee> updateList = Arrays.asList(new Employee("", ""));

		List<Employee> exfInsertList = Arrays.asList(new Employee("jjj", "111"));

		when(mockEmp.update(Matchers.anyListOf(Employee.class), anyObject())).thenReturn(exfInsertList);

		EmployeeRequest emp = EmployeeRequest.builder()
			.name("").birth("").changeName("jjj").changeBirth("111").build();

		updateList = mockEmp.update(updateList, emp);

		assertThat(exfInsertList, is(updateList));
	}

	@Test
	public void deleteTest() {
		List<Employee> deleteList = Arrays.asList(new Employee("jjj", "111"));

		List<Employee> exfInsertList = Collections.emptyList();

		when(mockEmp.delete(Matchers.anyListOf(Employee.class), anyObject())).thenReturn(exfInsertList);

		EmployeeRequest emp = EmployeeRequest.builder()
			.name("jjj").birth("111").changeName("").changeBirth("").build();

		deleteList = mockEmp.delete(deleteList, emp);

		assertThat(exfInsertList, is(deleteList));
	}
}