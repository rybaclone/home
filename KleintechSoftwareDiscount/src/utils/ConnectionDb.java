package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionDb {
	
	
	public ResultSet ConnectDb(String sql) {
		Connection conn = null;
		ResultSet rs = null;
						
		String usr = "root";
		String psw = "qwer12";
		String db = "test";
		String url = "jdbc:mysql://localhost:3306/" + db + "?useSSL=false";

		try {
			conn = DriverManager.getConnection(url, usr, psw);
			sql = "SELECT * FROM customer limit 5";
			Statement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);

		} catch (Exception ex) {
			System.err.println("Connection failed " + ex);
		}
		return rs;
	}

}
