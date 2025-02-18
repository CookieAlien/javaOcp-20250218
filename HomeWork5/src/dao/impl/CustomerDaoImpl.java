package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CustomerDao;
import model.Customer;
import util.DBConnection;

public class CustomerDaoImpl implements CustomerDao{
	private static Connection connection = DBConnection.getConnection();
	@Override
	public void add(Customer customer) {
		String sql = "insert into customer(customerno,username,password,name,address,email,phone) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getCustomerno());
			preparedStatement.setString(2, customer.getUsername());
			preparedStatement.setString(3, customer.getPassword());
			preparedStatement.setString(4, customer.getName());
			preparedStatement.setString(5, customer.getAddress());
			preparedStatement.setString(6, customer.getEmail());
			preparedStatement.setString(7, customer.getPhone());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Customer> selectAll() {
		String sql = "select * from customer";
		List<Customer> customers = new ArrayList<Customer>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setId(resultSet.getInt("id"));
				customer.setCustomerno(resultSet.getString("customerno"));
				customer.setUsername(resultSet.getString("username"));
				customer.setPassword(resultSet.getString("password"));
				customer.setName(resultSet.getString("name"));
				customer.setAddress(resultSet.getString("address"));
				customer.setEmail(resultSet.getString("email"));
				customer.setPhone(resultSet.getString("phone"));
				customers.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customers;
	}

	@Override
	public List<Customer> selectByUsername(String username) {
		String sql = "select * from customer where username=?";
		List<Customer> customers = new ArrayList<Customer>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setId(resultSet.getInt("id"));
				customer.setCustomerno(resultSet.getString("customerno"));
				customer.setUsername(resultSet.getString("username"));
				customer.setPassword(resultSet.getString("password"));
				customer.setName(resultSet.getString("name"));
				customer.setAddress(resultSet.getString("address"));
				customer.setEmail(resultSet.getString("email"));
				customer.setPhone(resultSet.getString("phone"));
				customers.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customers;
	}

	@Override
	public List<Customer> selectByUsernameAndPassword(String username, String password) {
		String sql = "select * from customer where username=? and password=?";
		List<Customer> customers = new ArrayList<Customer>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setId(resultSet.getInt("id"));
				customer.setCustomerno(resultSet.getString("customerno"));
				customer.setUsername(resultSet.getString("username"));
				customer.setPassword(resultSet.getString("password"));
				customer.setName(resultSet.getString("name"));
				customer.setAddress(resultSet.getString("address"));
				customer.setEmail(resultSet.getString("email"));
				customer.setPhone(resultSet.getString("phone"));
				customers.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customers;
	}

	@Override
	public void update(Customer customer) {
		String sql = "update customer set username=?,password=?,name=?,address=?,email=?,phone=? where customerno=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getUsername());
			preparedStatement.setString(2, customer.getPassword());
			preparedStatement.setString(3, customer.getName());
			preparedStatement.setString(4, customer.getAddress());
			preparedStatement.setString(5, customer.getEmail());
			preparedStatement.setString(6, customer.getPhone());
			preparedStatement.setString(7, customer.getCustomerno());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(String customerno) {
		String sql = "delete from customer where customerno=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, customerno);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
