package test;

import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import com.mysql.jdbc.PreparedStatement;

import model.Book;
import utils.BookServiceImpl;

public class TestApp {

	static Connection conn = null;
	static Properties prop = new Properties();
	static InputStream input = null;

	public static void main(String[] args) throws SQLException {

		doTestConn();
		testGetBookByName("Winnie-the-Pooh", "Alan Alexander Milne");

		testGetBookByName("WinniethePooh", "AlanAlexanderMilne");

		BookServiceImpl bs = new BookServiceImpl();

		int numberOfBooks = bs.getNumberOfBooks();
		System.out.println(numberOfBooks + " books are stored");

		int PAGESIZE = 25;

		for (int p = 0; p < numberOfBooks; p += PAGESIZE) {
			System.out.println("iterate page buttons " + p);
		}

		ArrayList<Book> lst = bs.getBookList(0, 50);

		// Generate test books
		for (int b = 0; b < 35; b++) {
			// bs.insertBook("Nosaukums_"+b, "Author_"+b, 10+b);
		}

		for (Book book : lst) {
			System.out.println(book.toString());
		}

		String sql = "SELECT * FROM book  limit 50";
		doTestSQL(sql);

	}

	@Test
	private static void doTestSQL(String sql) {
		Connection conn = getConnection();

	}

	@Test
	public void testIfConnectionNotNull() {
		Connection conn = getConnection();
		assertNotNull(conn);

	}

	private static Connection getConnection() {

		try {
			input = new FileInputStream("db.properties");
			prop.load(input);
			String usr = prop.getProperty("user");
			String psw = prop.getProperty("password");
			String url = prop.getProperty("url");

			conn = DriverManager.getConnection(url, usr, psw);
		} catch (Exception ex) {
			System.err.println("Connection failed " + ex);
		}
		return conn;
	}

	@Test
	public static void testUpdateBook(int id, String name, String author, double price) throws SQLException {
		{

			PreparedStatement updateBook = null;

			String updateStatement = "update test.book set name= ? , author = ? , price = ? where id = ?";
			try {
				conn.setAutoCommit(false);

				updateBook = (PreparedStatement) conn.prepareStatement(updateStatement);

				updateBook.setString(1, name);
				updateBook.setString(2, author);
				updateBook.setDouble(3, price);
				updateBook.setInt(4, id);

				updateBook.executeUpdate();
				conn.commit();

			} catch (SQLException e) {
				System.err.println(e.getMessage());
				if (conn != null) {
					try {
						System.err.print("Transaction is being rolled back");
						conn.rollback();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					}
				}
			} finally {

				if (updateBook != null) {
					try {
						updateBook.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				try {
					conn.setAutoCommit(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public static void testUpdateBookPrice(int id, double price) throws SQLException {

		PreparedStatement updatePrice = null;

		String updateStatement = "update test.book " + "set price =  ? " + "where id = ?";

		try {
			conn.setAutoCommit(false);

			updatePrice = (PreparedStatement) conn.prepareStatement(updateStatement);

			updatePrice.setInt(2, id);
			updatePrice.setDouble(1, price);

			updatePrice.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			if (conn != null) {
				try {
					System.err.print("Transaction is being rolled back");
					conn.rollback();
				} catch (SQLException ex) {
					System.err.println(ex.getMessage());
				}
			}
		} finally {

			if (updatePrice != null) {
				updatePrice.close();
			}
			conn.setAutoCommit(true);
		}
	}

	@Test
	public static void testGetBookByName(String name, String author) {

		Book book = new Book();

		PreparedStatement selectBook = null;
		try {
			String selectStatement = "select * from test.book where name = ? and author = ? limit 1";

			selectBook = (PreparedStatement) conn.prepareStatement(selectStatement);
			selectBook.setString(1, name);
			selectBook.setString(2, author);
			ResultSet rs = selectBook.executeQuery();

			if (rs.next()) {
				book.setBookId(rs.getInt("id"));
				book.setName(name);
				book.setAuthor(author);
				book.setPrice(rs.getDouble("price"));
				System.out.println("The book was found: " + book.toString());
			} else {
				System.out.println("The book was not found: " + name + " " + author);
			}

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());

		}
	}

	@Test
	private static void doTestConn() {

		try {
			input = new FileInputStream("db.properties");
			prop.load(input);
			String usr = prop.getProperty("user");
			String psw = prop.getProperty("password");
			String url = prop.getProperty("url");

			conn = DriverManager.getConnection(url, usr, psw);
			System.out.println("Connected success");

		} catch (Exception ex) {
			System.err.println("Connection failed " + ex);
		}

	}

}
