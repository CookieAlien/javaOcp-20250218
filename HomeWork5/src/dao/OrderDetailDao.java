package dao;

import java.util.List;

import model.OrderItem;

public interface OrderDetailDao {
	//create
	void add(OrderItem item);
	//read
	List<OrderItem> selectAll();
	List<OrderItem> selectByOrder(String orderno);
	List<OrderItem> selectByProduct(String productno);
	//update
	void update(OrderItem item);
	//delete
	void delete(String orderno, String productno);
	void delete(String orderno);
}
