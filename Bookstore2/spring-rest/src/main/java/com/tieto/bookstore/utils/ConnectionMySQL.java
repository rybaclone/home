package com.tieto.bookstore.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionMySQL {

	public Connection getConnection() {
		Connection conn = null;

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());

			  String usr = "root"; String psw = "qwer12"; String url =
			  "jdbc:mysql://localhost:3306/test?useSSL=false";			 
			conn = DriverManager.getConnection(url, usr, psw);
			System.out.println("Connected success");

		} catch (Exception ex) {
			System.err.println("Connection failed " + ex);
		}
		return conn;
	}
}
