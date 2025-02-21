package service.impl;

import java.util.List;

import dao.impl.CustomerDaoImpl;
import dao.impl.EmployeeDaoImpl;
import model.Employee;
import service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
	public static EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();
	public static CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
	public static void main(String[] args) {
		new EmployeeServiceImpl().deleteEmployee("e002");
	}
	
	@Override
	public void register(Employee employee) {
		employeeDaoImpl.add(employee);

	}

	@Override
	public Employee login(String username, String password) {
		Employee employee = null;
		List<Employee> result = employeeDaoImpl.selectByUsernameAndPassword(username, password);
		if (result.size()>0) {
			employee = result.get(0);
		}
		return employee;
	}

	@Override
	public boolean isUsernameTaken(String username) {
		return !(employeeDaoImpl.selectByUsername(username).isEmpty() && customerDaoImpl.selectByUsername(username).isEmpty());
	}

	@Override
	public String generateEmployeeno() {
		String lastno = employeeDaoImpl.selectLastEmployeeno();
		if (lastno!=null) {
			int num = Integer.parseInt(lastno.substring(1)) + 1;
			return String.format("e%03d", num);
		}else {
			return "e001";
		}
	}

	@Override
	public void updateInfo(Employee employee) {
		employeeDaoImpl.update(employee);

	}

	@Override
	public void deleteEmployee(String employeeno) {
		if (employeeno.matches("e[0-9]{3}")) {
			employeeDaoImpl.delete(employeeno);
		}

	}

}
