package com.mize.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DataBaseConnection {

	private static String driver=null;
	private static String url=null;
	private static String username=null;
	private static String password=null;
	private static Connection connection=null;

	public static void loadProperties()
	{
		FileInputStream fis=null;
		try {
			fis = new FileInputStream("E:/PlayFrameWork/EmployeeService/app/connection.properties");
			Properties properties=new Properties();
			properties.load(fis);
			driver=properties.getProperty("driver");
			url=properties.getProperty("url");
			username=properties.getProperty("username");
			password=properties.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public static Connection getConnection() {
		try {
			if (connection == null) {
				loadProperties();
				Class.forName(driver);
				connection = DriverManager.getConnection(url, username,
						password);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
}
