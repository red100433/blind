package com.school.business.crud;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.school.models.Type;
import com.school.models.vo.Employee;

@RunWith(Parameterized.class)
public class EmployeeCrudImpTest {

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{new Employee(null, null), false},
			{new Employee("jangTeacher", null), false},
			{new Employee(null, "123"), false},
			{new Employee("", ""), true},
			{new Employee("jangTT", "1234"), true}
		});
	}

	private List<Employee> list = new ArrayList<>();
	private Employee fInput;
	private Employee delete = new Employee("jangTT", "1234");

	private boolean fExpected;

	public EmployeeCrudImpTest(Employee input, boolean expected) {
		fInput = input;
		fExpected = expected;
	}

	@Before
	public void insert() {
		if (list.size() != Type.LIMIT_PERSON
			& list.contains(fInput) == false
			& EmployeeTest.test(fInput)) {
			list.add(fInput);
		}
		assertEquals(fExpected, list.contains(fInput));
	}

	//	@Test
	//	public List<Employee> update(List<Employee> list, String changeName, String changeBirth) {
	//		Employee change = new Employee(changeName, changeBirth);
	//		if (list.contains(temp) & (list.contains(change) == false)) {
	//			list.remove(temp);
	//			list.add(change);
	//		}
	//		return list;
	//	}

	@Test
	public void delete() {
		list.remove(delete);
		assertEquals(false, list.contains(fInput));
	}
}

class EmployeeTest {
	public static boolean test(Employee fInput) {
		boolean result = true;
		if (fInput.getBirth() == null) {
			result = false;
		}

		if (fInput.getEmployeeName() == null) {
			result = false;
		}

		return result;
	}
}
