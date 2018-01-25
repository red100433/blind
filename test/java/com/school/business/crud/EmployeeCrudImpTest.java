package com.school.business.crud;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.school.models.vo.Employee;

@RunWith(Parameterized.class)
public class EmployeeCrudImpTest {

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{new Employee("", ""), true},
			{new Employee("aaa", ""), true},
			{new Employee("", "222"), true},
			{new Employee("jangTT", "1234"), true}
		});
	}

	private List<EmployeeCrudImpTest> list = new ArrayList<>();
	private Employee fInput;
	private Employee delete = new Employee("jangTT", "1234");

	private boolean fExpected;

	public EmployeeCrudImpTest(Employee input, boolean expected) {
		fInput = input;
		fExpected = expected;
		list.add(this);
	}

	@Test
	public void insert() {
		//		if (list.size() != Type.LIMIT_PERSON
		//			& list.contains(fInput) == false) {
		//			list.add(fInput);
		//		}
		//		assertEquals(fExpected, list.contains(fInput));
		list.forEach(s -> System.out.println(s.fExpected));
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

	@Ignore
	@Test
	public void delete() {
		verify(list.remove(delete), atLeastOnce());
	}
}
