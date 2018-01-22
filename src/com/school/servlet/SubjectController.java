package com.school.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.school.models.Subject;
import com.school.models.Type;
import com.school.service.SubjectService;

public class SubjectController extends HttpServlet {
	SubjectService subjectService = SubjectService.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String management = req.getParameter("Manage");
		String crud = req.getParameter("Crud");
		String name = req.getParameter("name");
		String changeName = req.getParameter("changeName");
		switch (crud) {
			case Type.INSERT:
				subjectService.insert(name);
				break;
			case Type.UPDATE:
				subjectService.update(name, changeName);
				break;
			case Type.DELETE:
				subjectService.delete(name);
				break;
		}
		List<Subject> list = subjectService.select();

		System.out.println("ManageMent Data:" + management);
		System.out.println("Curd Data:" + crud);
		//
		//		list.forEach(System.out::println);
		//		fs.writeListObject(list, "C:\\Users\\NAVER\\Desktop\\java\\basicJava\\WebContent\\WEB-INF\\subObject.txt");
		req.setAttribute("menulist", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(req, res);
	}
}
