package com.school.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.school.models.Grade;
import com.school.models.Student;
import com.school.models.Subject;
import com.school.models.Type;
import com.school.service.GradeService;
import com.school.service.StudentService;
import com.school.service.SubjectService;

public class GradeController extends HttpServlet {
	GradeService gradeService = GradeService.getInstance();
	StudentService studentService = StudentService.getInstance();
	SubjectService subjectService = SubjectService.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String management = req.getParameter("Manage");
		String crud = req.getParameter("Crud");
		String selectOption = req.getParameter("selectOption");

		String name = req.getParameter("name");
		String grade = req.getParameter("grade");
		String subject = req.getParameter("subject");
		String changeName = req.getParameter("changeName");
		String changeGrade = req.getParameter("changBirth");
		String changeSubject = req.getParameter("changeSubject");

		List<String> list = Collections.emptyList();
		switch (crud) {
			case Type.INSERT:
				gradeService.insert(name, subject, Integer.parseInt(grade));
				break;
			case Type.UPDATE:
				gradeService.update(name, subject, changeName, changeSubject, Integer.parseInt(changeGrade));
				break;
			case Type.DELETE:
				gradeService.delete(name, subject);
				break;
			case Type.SLELCT:
				list = selectOption(selectOption, name);
				break;
		}
		if ("".equals(selectOption)) {
			list = selectOption(Type.ALL_SELECT, name);
		}

		System.out.println("ManageMent Data:" + management);
		System.out.println("Curd Data:" + crud);
		//
		//		list.forEach(System.out::println);
		//		fs.writeListObject(list, "C:\\Users\\NAVER\\Desktop\\java\\basicJava\\WebContent\\WEB-INF\\subObject.txt");
		req.setAttribute("menulist", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(req, res);
	}

	private List<String> selectOption(String selectOption, String name) {

		switch (selectOption) {
			case Type.ALL_SELECT:
				List<String> result = gradeService.select().stream()
					.map(o -> o.toString())
					.collect(Collectors.toList());

				gradeService.select().forEach(System.out::println);
				return result;
			case Type.STUDENT_SELECT:
				OptionalDouble studentAverage = getAverage(name);
				List<String> result1 = new ArrayList<>();
				studentAverage.ifPresent(o -> {
					System.out.println(name + "의 평균: " + o);
					result1.add(name + "의 평균: " + o);
				});
				return result1;
			case Type.ALL_STUDENT_AVERAGE_SELECT:
				List<String> result2 = new ArrayList<>();
				for (Student stu : studentService.select()) {
					OptionalDouble allStudentAverage = getAverage(stu.getStudentName());

					allStudentAverage.ifPresent(
						o -> {
							System.out.println(stu.getStudentName() + "의 평균: " + o);
							result2.add(stu.getStudentName() + "의 평균:" + o);
						});
				}
				return result2;
			case Type.ALL_SUBJECT_AVERAGE_SELECT:
				List<String> result3 = new ArrayList<>();
				for (Subject sub : subjectService.select()) {
					OptionalDouble allSubjectAverage = getAverage(sub.getSubjectName());

					allSubjectAverage.ifPresent(o -> {
						System.out.println(sub.getSubjectName() + "의 평균: " + o);
						result3.add(sub.getSubjectName() + "의 평균: " + o);
					});
				}
				return result3;
		}
		return Collections.emptyList();

	}

	private OptionalDouble getAverage(String name) {
		return gradeService.select().stream()
			.filter(s -> s.getStudentName().equals(name))
			.mapToInt(Grade::getGrade)
			.average();
	}
}
