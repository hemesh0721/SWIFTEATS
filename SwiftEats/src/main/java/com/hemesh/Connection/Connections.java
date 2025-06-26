package com.hemesh.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
	public static Connection getConnection() throws ClassNotFoundException,SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://maglev.proxy.rlwy.net:12000/railway?useSSL=true";
		String username = "root";
		String password="RDuUnSKvdswZlUAAcOwoBqIBErLmVsPq";
		Connection conn = DriverManager.getConnection(url,username,password);
		return conn;
	}
}
