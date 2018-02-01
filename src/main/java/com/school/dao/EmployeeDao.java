package com.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.school.db.DbConnection;
import com.school.models.vo.Employee;

/**
 *
 * @author daeyun-jang
 *
 */
public class EmployeeDao {
	private Connection connection;

	public EmployeeDao() {
		connection = DbConnection.getConnection();
	}

	public void addEmployee(Employee employee) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("insert into employee(employeeName, birth) values (?, ?)");
			// Parameters start with 1
			preparedStatement.setString(1, employee.getEmployeeName());
			preparedStatement.setString(2, employee.getBirth());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteEmployee(int emp_Id) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("delete from employee where emp_Id=?");
			// Parameters start with 1
			preparedStatement.setInt(1, emp_Id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateEmployee(Employee employee) {
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("update employee set employeeName=?, birth=?" +
					"where emp_Id=?");
			// Parameters start with 1
			preparedStatement.setString(1, employee.getEmployeeName());
			preparedStatement.setString(2, employee.getBirth());
			preparedStatement.setInt(3, employee.getEmp_Id());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Employee> getAllEmployees() {
		List<Employee> empList = new ArrayList<Employee>();
		try {
			PreparedStatement preparedStatement = connection
				.prepareStatement("select * from employee");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmp_Id(rs.getInt("emp_Id"));
				emp.setEmployeeName(rs.getString("employeeName"));
				emp.setBirth(rs.getString("birth"));
				empList.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empList;
	}

	public Employee getEmployeeById(int emp_Id) {
		Employee emp = new Employee();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from employee where emp_Id=?");
			preparedStatement.setInt(1, emp_Id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				emp.setEmp_Id(rs.getInt("emp_Id"));
				emp.setEmployeeName(rs.getString("employeeName"));
				emp.setBirth(rs.getString("birth"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return emp;
	}
}
