package com.school.models.vo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class EmployeeTest {

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

	private Employee fInput;

	private boolean fExpected;

	public EmployeeTest(Employee input, boolean expected) {
		fInput = input;
		fExpected = expected;
	}

	@Test
	public void test() {
		assertEquals("faile should equals", fExpected, EmployeeInputTest.test(fInput));
	}

}

class EmployeeInputTest {
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