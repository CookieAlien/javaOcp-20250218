package service;

import model.Employee;

public interface EmployeeService {
	void register(Employee employee);
	
	Employee login(String username, String password);
	boolean isUsernameTaken(String username);
	String generateEmployeeno();
	
	void updateInfo(Employee employee);
	
	void deleteEmployee(String employeeno);
	
}
