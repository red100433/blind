package com.school.models.vo;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.school.custom.DaoInterface;
import com.school.service.GradeService;

@RunWith(MockitoJUnitRunner.class)
public class GradeTest {

	@Mock
	DaoInterface dao;

	@InjectMocks
	GradeService gradeService;

	List<Grade> list = new ArrayList<>();

	//	@Before
	//	public void setUp() {
	//		list.add(new Grade("jang", "JAVA", 20));
	//		list.add(new Grade("jang", "C", 25));
	//		list.add(new Grade("jang", "C++", 30));
	//		list.add(new Grade("hong", "JAVA", 20));
	//		list.add(new Grade("hong", "C", 40));
	//	}
	//
	//	@Test
	//	public void getGradeByScore() {
	//		Grade grade = new Grade("jang", "JAVA", 20);
	//	}
}
