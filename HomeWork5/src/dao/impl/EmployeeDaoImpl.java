package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.EmployeeDao;
import model.Employee;
import util.DBConnection;

public class EmployeeDaoImpl implements EmployeeDao {
	private static Connection connection = DBConnection.getConnection();
	@Override
	public void add(Employee employee) {
		String sql = "insert into employee(employeeno,username,password,name,address,email,phone) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, employee.getEmployeeno());
			preparedStatement.setString(2, employee.getUsername());
			preparedStatement.setString(3, employee.getPassword());
			preparedStatement.setString(4, employee.getName());
			preparedStatement.setString(5, employee.getAddress());
			preparedStatement.setString(6, employee.getEmail());
			preparedStatement.setString(7, employee.getPhone());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Employee> selectAll() {
		String sql = "select * from employee";
		List<Employee> employees = new ArrayList<Employee>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getInt("id"));
				employee.setEmployeeno(resultSet.getString("employeeno"));
				employee.setUsername(resultSet.getString("username"));
				employee.setPassword(resultSet.getString("password"));
				employee.setName(resultSet.getString("name"));
				employee.setAddress(resultSet.getString("address"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPhone(resultSet.getString("phone"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employees;
	}

	@Override
	public List<Employee> selectByUsername(String username) {
		String sql = "select * from employee where username=?";
		List<Employee> employees = new ArrayList<Employee>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getInt("id"));
				employee.setEmployeeno(resultSet.getString("employeeno"));
				employee.setUsername(resultSet.getString("username"));
				employee.setPassword(resultSet.getString("password"));
				employee.setName(resultSet.getString("name"));
				employee.setAddress(resultSet.getString("address"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPhone(resultSet.getString("phone"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employees;
	}

	@Override
	public List<Employee> selectByUsernameAndPassword(String username, String password) {
		String sql = "select * from employee where username=? and password=?";
		List<Employee> employees = new ArrayList<Employee>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getInt("id"));
				employee.setEmployeeno(resultSet.getString("employeeno"));
				employee.setUsername(resultSet.getString("username"));
				employee.setPassword(resultSet.getString("password"));
				employee.setName(resultSet.getString("name"));
				employee.setAddress(resultSet.getString("address"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPhone(resultSet.getString("phone"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employees;
	}

	@Override
	public void update(Employee employee) {
		String sql = "update employee set username=?,password=?,name=?,address=?,email=?,phone=? where employeeno=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, employee.getUsername());
			preparedStatement.setString(2, employee.getPassword());
			preparedStatement.setString(3, employee.getName());
			preparedStatement.setString(4, employee.getAddress());
			preparedStatement.setString(5, employee.getEmail());
			preparedStatement.setString(6, employee.getPhone());
			preparedStatement.setString(7, employee.getEmployeeno());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(String employeeno) {
		String sql = "delete from employee where employeeno=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, employeeno);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
