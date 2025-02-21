package service;

import java.util.List;

import model.Customer;

public interface CustomerService {
	void register(Customer customer);
	
	List<Customer> getAllCustomers();
	Customer login(String username, String password);
	boolean isUsernameTaken(String username);
	String generateCustomerno();
	
	void updateInfo(Customer customer);
	
	void deleteCustomer(String customerno);
}
