package com.nhn.school.service;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nhn.school.SchoolManagerApplication;
import com.nhn.school.dao.EmployeeDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolManagerApplication.class)
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;
	
	@Mock
	private EmployeeDao dao;
	
	
}
