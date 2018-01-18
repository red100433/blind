package com.school.servlet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MessageLoader implements ServletContextListener {
	private Map<String, String> message = new HashMap<>();

	/**
	 * messageFile의 내용을 읽어 들여 context에 message라는 이름으로 setAttribute한다.
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {

		message = new HashMap<>();

		ServletContext context = event.getServletContext();

		String filename = context.getInitParameter("messageFile");

		try (InputStream is = context.getResourceAsStream(filename);
			BufferedReader in = new BufferedReader(new InputStreamReader(is))) {
			String line = "";
			while ((line = in.readLine()) != null) {
				String[] token = line.split(":");
				message.put(token[0], token[1]);
			}
			context.setAttribute("message", message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {}
}
