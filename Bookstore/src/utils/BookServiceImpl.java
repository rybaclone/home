package utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import model.Book;
import model.BookService;

public class BookServiceImpl implements BookService {

	static ConnectionMySQL connetion = new ConnectionMySQL();

	
	@Override
	public void updateBook(int id, String name, String author, double price) {
		
		Connection conn = connetion.getConnection();

		PreparedStatement updateBook = null;

		String updateStatement = "update test.book set name= ? ,author = ? ,price =  ?"
				+ " where id = ?";
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

	@Override
	public void insertBook(String name, String author, double price) {
		Connection conn = connetion.getConnection();
		PreparedStatement insertBook = null;
		String insertStatement = "insert into test.book"
				+ " (name, author, price)"
				+ " values (?,?,?)";

		try {
			conn.setAutoCommit(false);

			insertBook = (PreparedStatement) conn.prepareStatement(insertStatement);

			insertBook.setString(1, name);
			insertBook.setString(2, author);
			insertBook.setDouble(3, price);

			insertBook.executeUpdate();
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

			if (insertBook != null) {
				try {
					insertBook.close();
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


	
	public int getNumberOfBooks() {
		int count = 0;
		Connection conn = connetion.getConnection();
		PreparedStatement selectNumbersOfBooks = null;
		try {
			String selectStatement = 
					"select count(id) cnt from test.book";

			selectNumbersOfBooks = (PreparedStatement) conn.prepareStatement(selectStatement);
			ResultSet rs = selectNumbersOfBooks.executeQuery();
			rs.next();
			count = rs.getInt(1);
						
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return count;
	}
	
	public ArrayList<Book> getBookList( int page, int limit) {

		ArrayList<Book> lstBooks = new ArrayList<Book>();
		Connection conn = connetion.getConnection();
		PreparedStatement selectBooks = null;

		try {
			String selectStatement = 
					"select * from test.book"
					+ " order by id desc"
					+ " limit ?, ?";

			selectBooks = (PreparedStatement) conn.prepareStatement(selectStatement);

			selectBooks.setInt(1, limit);
			int start = page * limit;
			selectBooks.setInt(1, start);
			selectBooks.setInt(2, limit);

			ResultSet rs = selectBooks.executeQuery();

			while (rs.next()) {
				int bookId = rs.getInt("id");
				String name = rs.getString("name");
				String author = rs.getString("author");
				double price = rs.getDouble("price");

				Book book = new Book(bookId, name, author, price);
				lstBooks.add(book);
			}

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return lstBooks;
	}

	@Override
	public Book getBook(int bookId) {
		Book book = new Book();		
		Connection conn = connetion.getConnection();
		PreparedStatement selectBook = null;
		try {
			String selectStatement = 
					"select * from test.book where id = ?";

			selectBook = (PreparedStatement) conn.prepareStatement(selectStatement);
			selectBook.setInt(1, bookId);
			ResultSet rs = selectBook.executeQuery();
			
			rs.next();
				book.setBookId(bookId);
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setPrice(rs.getDouble("price"));
										
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
		return book;
	}

	@Override
	public Book getBook(String name, String author) {
	
		Book book = new Book();		
		Connection conn = connetion.getConnection();
		PreparedStatement selectBook = null;
		try {
			String selectStatement = 
					"select * from test.book where name = ? and author = ? limit 1";

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
				System.out.println("The book was not found: " + name +" " +author);
			}										
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return book;
	}

}
