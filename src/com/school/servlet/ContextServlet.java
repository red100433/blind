package com.school.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.school.business.Service;

public class ContextServlet extends HttpServlet {
	Service service = Service.getInstance();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 4, 5);
		req.setAttribute("menulist", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(req, res);
	}

	//My source
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		String management = req.getParameter("Manage");
		String crud = req.getParameter("Crud");
		String name = req.getParameter("name");
		String birth = req.getParameter("birth");

		List<?> list = service.readSubject();
		service.programStart(management, crud);
		//
		//		list.forEach(System.out::println);
		//		fs.writeListObject(list, "C:\\Users\\NAVER\\Desktop\\java\\basicJava\\WebContent\\WEB-INF\\subObject.txt");
		req.setAttribute("menulist", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(req, res);
	}
};