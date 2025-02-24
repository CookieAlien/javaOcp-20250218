package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.OrderDetailDao;
import model.OrderItem;
import util.DBConnection;

public class OrderDetailDaoImpl implements OrderDetailDao {
	Connection connection = DBConnection.getConnection();
	@Override
	public void add(OrderItem item) {
		String sql = "insert into orderdetails(porderno,productno,productname,amount,sum) values(?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, item.getPorderno());
			preparedStatement.setString(2, item.getProductno());
			preparedStatement.setString(3, item.getProductname());
			preparedStatement.setInt(4, item.getAmount());
			preparedStatement.setInt(5, item.getSum());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<OrderItem> selectAll() {
		String sql = "select * from orderdetails";
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		try {
			PreparedStatement preparedStatement =connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				OrderItem item = new OrderItem();
				item.setOrderDetailsID(resultSet.getInt("orderdetailid"));
				item.setPorderno(resultSet.getString("porderno"));
				item.setProductno(resultSet.getString("productno"));
				item.setAmount(resultSet.getInt("amount"));
				item.setSum(resultSet.getInt("sum"));
				orderItems.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderItems;
	}

	@Override
	public List<OrderItem> selectByOrder(String orderno) {
		String sql = "select * from orderdetails where porderno=?";
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		try {
			PreparedStatement preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, orderno);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				OrderItem item = new OrderItem();
				item.setOrderDetailsID(resultSet.getInt("orderdetailid"));
				item.setPorderno(resultSet.getString("porderno"));
				item.setProductno(resultSet.getString("productno"));
				item.setProductname(resultSet.getString("productname"));
				item.setAmount(resultSet.getInt("amount"));
				item.setSum(resultSet.getInt("sum"));
				orderItems.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderItems;
	}

	@Override
	public List<OrderItem> selectByProduct(String productno) {
		String sql = "select * from orderdetails where productno=?";
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		try {
			PreparedStatement preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, productno);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				OrderItem item = new OrderItem();
				item.setOrderDetailsID(resultSet.getInt("orderdetailid"));
				item.setPorderno(resultSet.getString("porderno"));
				item.setProductno(resultSet.getString("productno"));
				item.setProductname(resultSet.getString("productname"));
				item.setAmount(resultSet.getInt("amount"));
				item.setSum(resultSet.getInt("sum"));
				orderItems.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderItems;
	}

	@Override
	public void update(OrderItem item) {
		String sql= "update orderdetails set amount=?,sum=? where porderno=? and productno=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, item.getAmount());
			preparedStatement.setInt(2, item.getSum());
			preparedStatement.setString(3, item.getPorderno());
			preparedStatement.setString(4, item.getProductno());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String orderno, String productno) {
		String sql= "delete from orderdetails where porderno=? and productno=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, orderno);
			preparedStatement.setString(2, productno);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(String orderno) {
		String sql= "delete from orderdetails where porderno=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, orderno);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
