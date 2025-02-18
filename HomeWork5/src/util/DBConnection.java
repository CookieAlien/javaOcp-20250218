package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	private static String url;
	private static String user;
	private static String password;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(DBConnection.getConnection());
	}
	
	private static void readConfig() {
		Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream("db.txt")){
			properties.load(fis);
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConnection() {
		
		Connection connection = null;
		if (url==null) {
			readConfig();
		}
		
		try {
			connection = DriverManager.getConnection(url, user, password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}
