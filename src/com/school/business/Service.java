package com.school.business;

import java.util.List;
import java.util.Scanner;

import com.school.dao.EmployeeDao;
import com.school.dao.GradeDao;
import com.school.dao.StudentDao;
import com.school.dao.SubjectDao;
import com.school.dao.TeacherDao;
import com.school.inter.CrudInterface;
import com.school.inter.DaoInterface;
import com.school.models.Employee;
import com.school.models.Grade;
import com.school.models.Student;
import com.school.models.Subject;
import com.school.models.Teacher;

public class Service {

	private Scanner scanner = new Scanner(System.in);
	private CrudInterface crud;
	private DaoInterface dao;
	private List<Employee> empList;
	private List<Grade> gradeList;
	private List<Student> stuList;
	private List<Subject> subList;
	private List<Teacher> teacherList;

	public Service() {
		this.empList = new EmployeeDao().readDataList();
		this.gradeList = new GradeDao().readDataList();
		this.stuList = new StudentDao().readDataList();
		this.subList = new SubjectDao().readDataList();
		this.teacherList = new TeacherDao().readDataList();
	}

	public void programStart() {
		while (true) {
			System.out.println("1.인원관리  2.과목관리  3.성적관리  99.종료>>");
			String management = scanner.nextLine();

			if (management.equals("99")) {
				scanner.close();
				return;
			}

			System.out.println("1.입력   2.수정  3.삭제  4.조회>>");
			String crud = scanner.nextLine();

			switch (management) {
				case "1":
					System.out.println("1.학생 2.교직원 3.선생님>>");
					String person = scanner.nextLine();
					personnelManagement(crud, person, stuList, empList, teacherList);
					break;

				case "2":
					subjectManagement(crud, subList);
					break;

				case "3":
					performanceManagement(crud, gradeList, stuList, subList);
					break;
			}
		}
	}

	private void performanceManagement(String crudString, List<Grade> gradeList, List<Student> stuList,
		List<Subject> subList) {

		switch (crudString) {
			case "1":
				crud.insert(subList);
				break;
			case "2":
				crud.update(subList);
				break;
			case "3":
				crud.delete(subList);
				break;
			case "4":
				gradeList.forEach(System.out::println);
				break;
		}

		if (crudString.equals("4")) {
			//전체 조회
			gradeList.stream().forEach(System.out::println);

			//TODO: 학생별 조회
			//TODO: 학생 평균
			//TODO: 전체 평균
		}
	}

	private void subjectManagement(String crudString, List<Subject> subList) {
		//TODO: 입력 수정 삭제 조회
		switch (crudString) {
			case "1":
				crud.insert(subList);
				break;
			case "2":
				crud.update(subList);
				break;
			case "3":
				crud.delete(subList);
				break;
			case "4":
				subList.forEach(System.out::println);
				break;
		}
	}

	private void personnelManagement(String crudString, String person, List<Student> stuList, List<Employee> empList,
		List<Teacher> teacherList) {
		//TODO: 학생, 교직원, 선생별 별도 관리

		switch (crudString) {
			case "1":
				if (person.equals("1")) {
					crud.insert(stuList);
				} else if (person.equals("2")) {
					crud.insert(empList);
				} else if (person.equals("3")) {
					crud.insert(teacherList);
				}
				break;
			case "2":
				if (person.equals("1")) {
					crud.update(stuList);
				} else if (person.equals("2")) {
					crud.update(empList);
				} else if (person.equals("3")) {
					crud.update(teacherList);
				}

				break;
			case "3":

				if (person.equals("1")) {
					crud.delete(stuList);
				} else if (person.equals("2")) {
					crud.delete(empList);
				} else if (person.equals("3")) {
					crud.delete(empList);
				}

				break;
			case "4":
				if (person.equals("1")) {
					stuList.forEach(System.out::println);
				} else if (person.equals("2")) {
					empList.forEach(System.out::println);
				} else if (person.equals("3")) {
					teacherList.forEach(System.out::println);
				}
				break;
		}
	}

}