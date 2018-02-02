package com.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.school.db.DBConnection;
import com.school.models.vo.Employee;

/**
 *
 * @author daeyun-jang
 *
 */
public class EmployeeDao {
	private Connection connection;
	private static EmployeeDao t;

	public static EmployeeDao getInstance() {
		synchronized (EmployeeDao.class) {
			if (t == null) {
				t = new EmployeeDao();
			}
		}
		return t;
	}

	public EmployeeDao() {
		connection = DBConnection.getConnection();
	}

	public void addEmployee(Employee employee) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO employee(employeeName, birth) VALUES (?, ?)");
			// Parameters start with 1
			preparedStatement.setString(1, employee.getEmployeeName());
			preparedStatement.setString(2, employee.getBirth());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteEmployee(int empId) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("DELETE FROM employee WHERE empId=?");
			// Parameters start with 1
			preparedStatement.setInt(1, empId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateEmployee(Employee employee) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("UPDATE employee SET employeeName=?, birth=?" +
					"WHERE empId=?");
			// Parameters start with 1
			preparedStatement.setString(1, employee.getEmployeeName());
			preparedStatement.setString(2, employee.getBirth());
			preparedStatement.setInt(3, employee.getEmpId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Employee> getAllEmployees() {
		List<Employee> empList = new ArrayList<Employee>();
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT empId, employeeName, birth FROM employee");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Employee emp = Employee.of(rs.getString("empId"), rs.getString("employeeName"), rs.getString("birth"));
				empList.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empList;
	}

	public Employee getEmployeeById(int empId) {
		Employee emp = new Employee();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE empId=?");
			preparedStatement.setInt(1, empId);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				emp = Employee.of(rs.getString("empId"), rs.getString("employeeName"), rs.getString("birth"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return emp;
	}
}
