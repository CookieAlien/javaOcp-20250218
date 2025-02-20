package service.impl;

import java.util.List;

import dao.impl.ProductDaoImpl;
import model.Product;
import service.ProductService;

public class ProductServiceImpl implements ProductService {
	private static ProductDaoImpl productDaoImpl = new ProductDaoImpl();
	@Override
	public void addProduct(Product product) {
		if (!product.getProductname().isBlank() && product.getPrice()>0) {
			productDaoImpl.add(product);
		}

	}

	@Override
	public List<Product> getAllProducts() {
		return productDaoImpl.selectAll();
	}

	@Override
	public Product getProductByNo(String productno) {
		Product product = null;
		List<Product> result = productDaoImpl.selectByProductno(productno);
		if (result.size()>0) {
			product = result.get(0);
		}
		return product;
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		// TODO Auto-generated method stub
		return productDaoImpl.selectByCategory(category);
	}

	@Override
	public List<Product> getProductsByStatus(String status) {
		return productDaoImpl.selectByStatus(status);
	}

	@Override
	public void updateProduct(Product product) {
		if (!product.getProductname().isBlank() && product.getPrice()>0) {
			productDaoImpl.update(product);
		}

	}

	@Override
	public void deleteProduct(String productno) {
		productDaoImpl.delete(productno);

	}

}
