package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.PorderDao;
import model.Porder;
import util.DBConnection;

public class PorderDaoImpl implements PorderDao {
	Connection connection = DBConnection.getConnection();
	@Override
	public void add(Porder order) {
		String sql = "insert into porder(porderno,customerno,employeeno,totalprice) values(?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, order.getPorderno());
			preparedStatement.setString(2, order.getCustomerno());
			preparedStatement.setString(3, order.getEmployeeno());
			preparedStatement.setInt(4, order.getTotalPrice());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	@Override
	public List<Porder> selectAll() {
		String sql = "select * from porder";
		List<Porder> orders = new ArrayList<Porder>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Porder porder = new Porder();
				porder.setPorderno(resultSet.getString("porderno"));
				porder.setCustomerno(resultSet.getString("customerno"));
				porder.setEmployeeno(resultSet.getString("employeeno"));
				porder.setTotalPrice(resultSet.getInt("totalprice"));
				Timestamp timestamp = resultSet.getTimestamp("last_modified");
				LocalDateTime dateTime = timestamp.toLocalDateTime();
				porder.setLastModified(dateTime);
				orders.add(porder);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public List<Porder> selectByCustomer(String customerno) {
		String sql = "select * from porder where costomerno=?";
		List<Porder> orders = new ArrayList<Porder>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customerno);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Porder porder = new Porder();
				porder.setPorderno(resultSet.getString("porderno"));
				porder.setCustomerno(resultSet.getString("customerno"));
				porder.setEmployeeno(resultSet.getString("employeeno"));
				porder.setTotalPrice(resultSet.getInt("totalprice"));
				Timestamp timestamp = resultSet.getTimestamp("last_modified");
				LocalDateTime dateTime = timestamp.toLocalDateTime();
				porder.setLastModified(dateTime);
				orders.add(porder);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public List<Porder> selectByEmployee(String employeeno) {
		String sql = "select * from porder where employeeno=?";
		List<Porder> orders = new ArrayList<Porder>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, employeeno);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Porder porder = new Porder();
				porder.setPorderno(resultSet.getString("porderno"));
				porder.setCustomerno(resultSet.getString("customerno"));
				porder.setEmployeeno(resultSet.getString("employeeno"));
				porder.setTotalPrice(resultSet.getInt("totalprice"));
				Timestamp timestamp = resultSet.getTimestamp("last_modified");
				LocalDateTime dateTime = timestamp.toLocalDateTime();
				porder.setLastModified(dateTime);
				orders.add(porder);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public List<Porder> selectByPorderno(String porderno) {
		String sql = "select * from porder where porderno=?";
		List<Porder> orders = new ArrayList<Porder>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, porderno);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Porder porder = new Porder();
				porder.setPorderno(resultSet.getString("porderno"));
				porder.setCustomerno(resultSet.getString("customerno"));
				porder.setEmployeeno(resultSet.getString("employeeno"));
				porder.setTotalPrice(resultSet.getInt("totalprice"));
				Timestamp timestamp = resultSet.getTimestamp("last_modified");
				LocalDateTime dateTime = timestamp.toLocalDateTime();
				porder.setLastModified(dateTime);
				orders.add(porder);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public void update(Porder order) {
		String sql = "update porder set customerno=?,employeeno=?,totalprice=? where porderno=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, order.getCustomerno());
			preparedStatement.setString(2, order.getEmployeeno());
			preparedStatement.setString(3, order.getPorderno());
			preparedStatement.setInt(4, order.getTotalPrice());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(String porderno) {
		String sql = "delete from porder where porderno=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, porderno);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String SelectLastPorderno() {
		String sql = "select max(porderno) from porder";
		String output = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				output = resultSet.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}

}
