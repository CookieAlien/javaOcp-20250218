package service;

import java.util.List;

import model.Employee;

public interface EmployeeService {
	void register(Employee employee);
	
	List<Employee> getAllEmployees();
	Employee login(String username, String password);
	boolean isUsernameTaken(String username);
	String generateEmployeeno();
	
	void updateInfo(Employee employee);
	
	void deleteEmployee(String employeeno);
	
}
