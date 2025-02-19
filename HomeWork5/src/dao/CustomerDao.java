package dao;

import java.util.List;

import model.Customer;

public interface CustomerDao {
	//create
	void add(Customer customer);
	//read
	List<Customer> selectAll();
	List<Customer> selectByUsername(String username);
	List<Customer> selectByUsernameAndPassword(String username, String password);
	String selectLastCustomerno();
	//update
	void update(Customer customer);
	//delete
	void delete(String customerno);
}
