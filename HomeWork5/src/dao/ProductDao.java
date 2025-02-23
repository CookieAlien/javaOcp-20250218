package dao;

import java.util.List;

import model.Product;

public interface ProductDao {
	//create
	void add(Product product);
	//read
	List<Product> selectAll();
	List<Product> selectByProductno(String productno);
	List<Product> selectByCategory(String category);
	List<Product> selectByStatus(String status);
	String selectLastProductno();
	//update
	void update(Product product);
	//delete
	void delete(String productno);
}
