package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProductDao;
import model.Product;
import util.DBConnection;

public class ProductDaoImpl implements ProductDao{
	Connection connection = DBConnection.getConnection();
	@Override
	public void add(Product product) {
		String sql = "insert into product(productno,productname,price,category,status) values(?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, product.getProductno());
			preparedStatement.setString(2, product.getProductname());
			preparedStatement.setInt(3, product.getPrice());
			preparedStatement.setString(4,product.getCategory());
			preparedStatement.setString(5, product.getStatus());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Product> selectAll() {
		String sql= "select * from product";
		List<Product> products = new ArrayList<Product>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("id"));
				product.setProductno(resultSet.getString("productno"));
				product.setProductname(resultSet.getString("productname"));
				product.setPrice(resultSet.getInt("price"));
				product.setCategory(resultSet.getString("category"));
				product.setStatus(resultSet.getString("status"));
				products.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public List<Product> selectByCategory(String category) {
		String sql= "select * from product where category=?";
		List<Product> products = new ArrayList<Product>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, category);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("id"));
				product.setProductno(resultSet.getString("productno"));
				product.setProductname(resultSet.getString("productname"));
				product.setPrice(resultSet.getInt("price"));
				product.setCategory(resultSet.getString("category"));
				product.setStatus(resultSet.getString("status"));
				products.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public void update(Product product) {
		String sql = "update product set productname=?,price=?,category=?,status=? where productno=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, product.getProductname());
			preparedStatement.setInt(2, product.getPrice());
			preparedStatement.setString(3,product.getCategory());
			preparedStatement.setString(4, product.getStatus());
			preparedStatement.setString(5, product.getProductno());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(String productno) {
		String sql = "delete from product where productno=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productno);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
