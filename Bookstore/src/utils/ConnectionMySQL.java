package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionMySQL {
	
	
	public Connection getConnection(){
		Connection conn = null;
			
		Properties prop = new Properties();
	    InputStream input = null;
	   
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
			
		/*	 input = new FileInputStream("db.properties");
			 prop.load(input);
			 		
				String usr = prop.getProperty("user"); 				
				String psw = prop.getProperty("password");				
				String url = prop.getProperty("url"); 
				*/
			String usr = "root"; 				
			String psw = "qwer12";				
			String url = "jdbc:mysql://localhost:3306/test?useSSL=false"; 
			
			conn = DriverManager.getConnection(url, usr, psw);
			System.out.println("Connected success");
			
		} catch (Exception ex) {
			System.err.println("Connection failed " + ex);
		}
		return conn;
	}

}
