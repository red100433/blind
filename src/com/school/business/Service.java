package com.school.business;

import java.util.List;
import java.util.Scanner;
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
import com.school.models.Employee;
import com.school.models.Grade;
import com.school.models.Student;
import com.school.models.Subject;
import com.school.models.Teacher;

/**
 *
 * @author daeyun-jang
 *
 */

//Main Service Logic
public class Service {

	private Scanner scanner = new Scanner(System.in);
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
			int person = 0;
			if (management.equals("99")) {
				scanner.close();
				new FileSystemManagement().wirte(stuList, empList, teacherList, subList,
					gradeList);
				return;
			}

			System.out.println("1.입력   2.수정  3.삭제  4.조회>>");
			String crud = scanner.nextLine();

			switch (management) {
				case "1":
					System.out.println("1.학생 2.교직원 3.선생님>>");
					person = Integer.parseInt(scanner.nextLine());
					personnelManagement(crud, person, stuList, empList, teacherList);
					break;

				case "2":
					subjectManagement(crud, subList);
					break;

				case "3":
					performanceManagement(crud, gradeList, stuList, subList);
					break;
			}

			if (!crud.equals("4")) {
				int revperson = person;
				Executors.newSingleThreadExecutor().execute(() -> {
					new FileSystemManagement().wirte(management, revperson, stuList, empList, teacherList, subList,
						gradeList);
				});
			}
		}
	}

	//Grade manageMent
	private void performanceManagement(String crudString, List<Grade> gradeList, List<Student> stuList,
		List<Subject> subList) {

		//TODO:삽입과 수정이 이루어 질때, 과목과 학생리스트에 있는지 검증절차를 진행 해야됌
		switch (crudString) {
			case "1":
				this.gradeList = new GradeCrud().insert(gradeList);
				break;
			case "2":
				this.gradeList = new GradeCrud().update(gradeList);
				break;
			case "3":
				this.gradeList = new GradeCrud().delete(gradeList);
				break;
			case "4":
				System.out.println("1.전체조회 2.학생별 조회 3.학생 평균 4.전체 평균>>");
				int check = Integer.parseInt(scanner.nextLine());

				if (check == 1) {
					gradeList.forEach(System.out::println);
				} else if (check == 2) {
					System.out.println("학생 이름>>");
					String name = scanner.nextLine();
					gradeList.stream().filter(s -> s.getStudentName().equals(name)).forEach(System.out::println);
					double average = gradeList.stream().filter(s -> s.getStudentName().equals(name))
						.mapToInt(Grade::getGrade).average().getAsDouble();
					System.out.println(name + "의 평균: " + average);
				} else if (check == 3) {
					for (Student stu : stuList) {
						double average = gradeList.stream().filter(s -> s.getStudentName().equals(stu.getStudentName()))
							.mapToInt(Grade::getGrade).average().getAsDouble();
						System.out.println(stu.getStudentName() + "의 평균: " + average);
					}
				} else if (check == 4) {
					for (Student stu : stuList) {
						double average = gradeList.stream().filter(s -> s.getStudentName().equals(stu.getStudentName()))
							.mapToInt(Grade::getGrade).average().getAsDouble();
						System.out.println(stu.getStudentName() + "의 평균: " + average);
					}

					for (Subject sub : subList) {
						double average = gradeList.stream().filter(s -> s.getSubjectName().equals(sub.getSubjectName()))
							.mapToInt(Grade::getGrade).average().getAsDouble();
						System.out.println(sub.getSubjectName() + "의 평균: " + average);
					}
				}
				break;
		}
	}

	//Subject Management
	private void subjectManagement(String crudString, List<Subject> subList) {
		switch (crudString) {
			case "1":
				this.subList = new SubjectCrud().insert(subList);
				break;
			case "2":
				this.subList = new SubjectCrud().update(subList);
				break;
			case "3":
				this.subList = new SubjectCrud().delete(subList);
				break;
			case "4":
				subList.forEach(System.out::println);
				break;
		}
	}

	//person Management
	private void personnelManagement(String crudString, int person, List<Student> stuList, List<Employee> empList,
		List<Teacher> teacherList) {
		//TODO: 선생님의 경우 삽입과 수정이 이루어질 때, 과목리스트에 실제로 과목이 있는지 체크해야됌
		switch (crudString) {
			case "1":
				if (person == 1) {
					this.stuList = new StudentCrud().insert(stuList);
				} else if (person == 2) {
					this.empList = new EmployeeCrud().insert(empList);
				} else if (person == 3) {
					this.teacherList = new TeacherCrud().insert(teacherList);
				}
				break;
			case "2":
				if (person == 1) {
					this.stuList = new StudentCrud().update(stuList);
				} else if (person == 2) {
					this.empList = new EmployeeCrud().update(empList);
				} else if (person == 3) {
					this.teacherList = new TeacherCrud().update(teacherList);
				}

				break;
			case "3":

				if (person == 1) {
					this.stuList = new StudentCrud().delete(stuList);
				} else if (person == 2) {
					this.empList = new EmployeeCrud().delete(empList);
				} else if (person == 3) {
					this.teacherList = new TeacherCrud().delete(teacherList);
				}

				break;
			case "4":
				if (person == 1) {
					stuList.forEach(System.out::println);
				} else if (person == 2) {
					empList.forEach(System.out::println);
				} else if (person == 3) {
					teacherList.forEach(System.out::println);
				}
				break;
		}
	}

}