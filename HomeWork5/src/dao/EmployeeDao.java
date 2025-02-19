package dao;

import java.util.List;

import model.Employee;

public interface EmployeeDao {
	//create
	void add(Employee employee);
	//read
	List<Employee> selectAll();
	List<Employee> selectByUsername(String username);
	List<Employee> selectByUsernameAndPassword(String username, String password);
	String selectLastEmployeeno();
	//update
	void update(Employee employee);
	//delete
	void delete(String employeeno);
}
