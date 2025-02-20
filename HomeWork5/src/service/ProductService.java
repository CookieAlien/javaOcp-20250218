package service;

import java.util.List;

import model.Product;

public interface ProductService {
	//create
	void addProduct(Product product);
	//read
	List<Product> getAllProducts();
	Product getProductByNo(String productno);
	List<Product> getProductsByCategory(String category);
	List<Product> getProductsByStatus(String status);
	//update
	void updateProduct(Product product);
	//delete
	void deleteProduct(String productno);
}
