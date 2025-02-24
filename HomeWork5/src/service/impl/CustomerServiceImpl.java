package service.impl;

import java.util.List;

import dao.impl.CustomerDaoImpl;
import dao.impl.EmployeeDaoImpl;
import model.Customer;
import service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	public static CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
	public static EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();
	public static void main(String[] args) {
		System.out.println(new CustomerServiceImpl().generateCustomerno());
	}
	
	@Override
	public void register(Customer customer) {
		customerDaoImpl.add(customer);

	}

	@Override
	public Customer login(String username, String password) {
		Customer customer = null;
		List<Customer> result = customerDaoImpl.selectByUsernameAndPassword(username, password);
		if (result.size()>0) {
			customer=result.get(0);
		}
		return customer;
	}

	@Override
	public boolean isUsernameTaken(String username) {
		return !(customerDaoImpl.selectByUsername(username).isEmpty() && employeeDaoImpl.selectByUsername(username).isEmpty());
	}

	@Override
	public String generateCustomerno() {
		String lastno = customerDaoImpl.selectLastCustomerno();
		if (!lastno.isBlank()) {
			int num = Integer.parseInt(lastno.substring(1)) + 1;
			return String.format("c%03d", num);
		} else {
			return "c001";
		}
	}

	@Override
	public void updateInfo(Customer customer) {
		customerDaoImpl.update(customer);

	}

	@Override
	public void deleteCustomer(String customerno) {
		if (customerno.matches("c[0-9]{3}")) {
			customerDaoImpl.delete(customerno);
		}

	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerDaoImpl.selectAll();
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		Customer customer = null;
		List<Customer> list = customerDaoImpl.selectByUsername(username);
		if (list.size()>0) {
			customer = list.get(0);
		}
		return customer;
	}

}
