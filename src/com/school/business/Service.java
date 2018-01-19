package com.school.business;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.school.business.crud.EmployeeCrud;
import com.school.business.crud.GradeCrud;
import com.school.business.crud.StudentCrud;
import com.school.business.crud.SubjectCrud;
import com.school.business.crud.TeacherCrud;
import com.school.dao.EmployeeDao;
import com.school.dao.GradeDao;
import com.school.dao.StudentDao;
import com.school.dao.SubjectDao;
import com.school.dao.TeacherDao;
import com.school.handler.ScannerHandler;
import com.school.models.Employee;
import com.school.models.Grade;
import com.school.models.Student;
import com.school.models.Subject;
import com.school.models.Teacher;
import com.school.view.GradeUI;
import com.school.view.ServiceUI;

/**
 *
 * @author daeyun-jang
 *
 */

//Main Service Logic
public class Service {
	private static final String EXIT = "99";
	private static final String PERSON_MANAGE = "1";
	private static final String SUBJECT_MANAGE = "2";
	private static final String GRADE_MANAGE = "3";

	private static final String INSERT = "1";
	private static final String UPDATE = "2";
	private static final String DELETE = "3";
	private static final String SELECT = "4";

	private static final int STUDENT = 1;
	private static final int EMPLOYEE = 2;
	private static final int TEACHER = 3;

	private List<Employee> empList;
	private List<Grade> gradeList;
	private List<Student> stuList;
	private List<Subject> subList;
	private List<Teacher> teacherList;
	private final ServiceUI serviceUi;
	private final ScannerHandler scanner;
	private final GradeUI gradeUI;

	private static Service t;

	public static Service getInstance() {
		synchronized (Service.class) {
			if (t == null) {
				t = new Service();
			}
		}
		return t;
	}

	private Service() {
		this.empList = new EmployeeDao().readDataList();
		this.gradeList = new GradeDao().readDataList();
		this.stuList = new StudentDao().readDataList();
		this.subList = new SubjectDao().readDataList();
		this.teacherList = new TeacherDao().readDataList();
		this.scanner = new ScannerHandler();
		this.serviceUi = new ServiceUI(scanner.getScanner());
		this.gradeUI = new GradeUI(scanner.getScanner());
	}

	public List<Subject> readSubject() {
		return this.subList;
	}

	public void programStart() {
		ExecutorService executor = Executors.newSingleThreadExecutor();

		while (true) {
			String management = serviceUi.selectManageMent();
			int person = 0;
			if (management.equals(EXIT)) {
				new FileSystemManagement().wirte(stuList, empList, teacherList, subList,
					gradeList);
				scanner.close();
				executor.shutdown();
				return;
			}

			String crud = serviceUi.selectCrud();

			switch (management) {
				case PERSON_MANAGE:
					person = serviceUi.selectPerson();
					personnelManagement(crud, person, stuList, empList, teacherList, subList);
					break;

				case SUBJECT_MANAGE:
					subjectManagement(crud, subList);
					break;

				case GRADE_MANAGE:
					performanceManagement(crud, gradeList, stuList, subList);
					break;
			}

			//조회를 제외한 수정 삭제 입력일떄, Thread를 생성해서 FileSystem에 저장함
			if (!crud.equals(SELECT)) {
				int revperson = person;
				executor.submit(() -> {
					new FileSystemManagement().wirte(management, revperson, stuList, empList, teacherList, subList,
						gradeList);
				});
			}
		}
	}

	//Grade manageMent
	private void performanceManagement(String crudString, List<Grade> gradeList, List<Student> stuList,
		List<Subject> subList) {

		switch (crudString) {
			case INSERT:
				this.gradeList = new GradeCrud(subList, stuList).insert(gradeList);
				break;
			case UPDATE:
				this.gradeList = new GradeCrud(subList, stuList).update(gradeList);
				break;
			case DELETE:
				this.gradeList = new GradeCrud().delete(gradeList);
				break;
			case SELECT:
				gradeUI.getGradePrint(gradeList, stuList, subList);
				break;
		}
	}

	//Subject Management
	private void subjectManagement(String crudString, List<Subject> subList) {
		switch (crudString) {
			case INSERT:
				this.subList = new SubjectCrud().insert(subList);
				break;
			case UPDATE:
				this.subList = new SubjectCrud().update(subList);
				break;
			case DELETE:
				this.subList = new SubjectCrud().delete(subList);
				break;
			case SELECT:
				subList.forEach(System.out::println);
				break;
		}
	}

	//person Management
	private void personnelManagement(String crudString, int person, List<Student> stuList, List<Employee> empList,
		List<Teacher> teacherList, List<Subject> subList) {
		switch (crudString) {
			case INSERT:
				if (person == STUDENT) {
					this.stuList = new StudentCrud().insert(stuList);
				} else if (person == EMPLOYEE) {
					this.empList = new EmployeeCrud().insert(empList);
				} else if (person == TEACHER) {
					this.teacherList = new TeacherCrud(subList).insert(teacherList);
				}
				break;
			case UPDATE:
				if (person == STUDENT) {
					this.stuList = new StudentCrud().update(stuList);
				} else if (person == EMPLOYEE) {
					this.empList = new EmployeeCrud().update(empList);
				} else if (person == TEACHER) {
					this.teacherList = new TeacherCrud(subList).update(teacherList);
				}

				break;
			case DELETE:

				if (person == STUDENT) {
					this.stuList = new StudentCrud().delete(stuList);
				} else if (person == EMPLOYEE) {
					this.empList = new EmployeeCrud().delete(empList);
				} else if (person == TEACHER) {
					this.teacherList = new TeacherCrud().delete(teacherList);
				}

				break;
			case SELECT:
				if (person == STUDENT) {
					stuList.forEach(System.out::println);
				} else if (person == EMPLOYEE) {
					empList.forEach(System.out::println);
				} else if (person == TEACHER) {
					teacherList.forEach(System.out::println);
				}
				break;
		}
	}

}