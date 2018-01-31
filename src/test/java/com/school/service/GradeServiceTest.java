package com.school.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.school.models.Type;
import com.school.models.vo.Grade;

@RunWith(Parameterized.class)
public class GradeServiceTest {

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			//			{new Grade("", ""), false},
			//			{new Grade("TT", "Java"), true},
			//			{new Grade("TT", "Java", 50), true},
			//			{new Grade("TT", "C"), true},
			//			{new Grade("TT", "C", 30), true},
			//			{new Grade("TT", "C++"), true},
			//			{new Grade("TT", "C++", 40), true},
			//			{new Grade("jang", "Java"), true},
			//			{new Grade("jang", "Java", 80), true},
			//			{new Grade("jang", "C++"), true},
			//			{new Grade("jang", "C++", 10), true},
			//			{new Grade("jang", "C"), true},
			//			{new Grade("jang", "C", 5), true}
		});
	}

	private List<Grade> list = new ArrayList<>();
	private Grade fInput;

	private boolean fExpected;

	public GradeServiceTest(Grade input, boolean expected) {
		fInput = input;
		fExpected = expected;
	}

	@Before
	public void setUp() throws Exception {}

	@Ignore
	@Test
	public void testGetInstance() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Ignore
	@Test
	public void testWriteFileSystem() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testInsert() throws Exception {
		if (list.size() != Type.LIMIT_PERSON
			& list.contains(fInput) == false
			& GradeObjTest.test(fInput)) {
			list.add(fInput);
		}
		assertEquals(fExpected, list.contains(fInput));
	}

	@Test
	public void testUpdateGradeRequest() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testUpdateStringString() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testDeleteGradeRequest() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testDeleteString() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testSelect() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testSelectOption() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

}

class GradeObjTest {
	public static boolean test(Grade fInput) {
		boolean result = true;
		//		if (fInput.getStudentName().equals("")) {
		//			result = false;
		//		}
		//
		//		if (fInput.getSubjectName().equals("")) {
		//			result = false;
		//		}

		return result;
	}
}
