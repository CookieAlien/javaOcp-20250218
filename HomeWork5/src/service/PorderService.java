package service;

import java.util.List;

import model.CartItem;
import model.Customer;
import model.Employee;
import model.OrderItem;
import model.Porder;

public interface PorderService {
	//create
	void createOrder(Porder porder, List<CartItem> items);
	
	//read
	List<Porder> getAllPorders();
	List<Porder> getPordersByCustomer(String customerno);
	List<Porder> getPordersByEmployee(String Employeeno);
	List<OrderItem> getOrderItems(String porderno);
	String generateOrderno();
	
	//update
	void updateOrder(Porder porder);
	void updateOrderitem(OrderItem item);
	//delete
	void deleteOrder(String porderno);
}
