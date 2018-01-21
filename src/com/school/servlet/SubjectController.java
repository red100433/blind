package com.school.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.school.business.Service;
import com.school.models.Subject;
import com.school.service.SubjectService;

public class SubjectController {
	private static final String INSERT = "INSERT";
	private static final String UPDATE = "UPDATE";
	private static final String DELETE = "DELETE";
	SubjectService subjectService = SubjectService.getInstance();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String management = req.getParameter("Manage");
		String crud = req.getParameter("Crud");
		String name = req.getParameter("name");
		String changeName = req.getParameter("changeName");
		switch(crud) {
			case INSERT :
				subjectService.insert(name);
				break;
			case UPDATE :
				subjectService.update(name, changeName);
				break;
			case DELETE :
				subjectService.delete(name);
				break;
		}
		List<Subject> list = subjectService.select();
		
		System.out.println("ManageMent Data:" +management);
		System.out.println("Curd Data:" + crud);
		//
		//		list.forEach(System.out::println);
		//		fs.writeListObject(list, "C:\\Users\\NAVER\\Desktop\\java\\basicJava\\WebContent\\WEB-INF\\subObject.txt");
		req.setAttribute("menulist", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(req, res);
	}
}
