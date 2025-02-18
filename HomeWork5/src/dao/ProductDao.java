package dao;

import java.util.List;

import model.Product;

public interface ProductDao {
	//create
	void add(Product product);
	//read
	List<Product> selectAll();
	List<Product> selectByCategory(String category);
	//update
	void update(Product product);
	//delete
	void delete(String productno);
}
