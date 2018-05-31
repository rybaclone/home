package com.tieto.bookstore.model;
//import javax.ws.rs.Path;

//@Path("/book");
public class Book {
	private int bookId;
	private String name;
	private String author;
	private double price;

	
	public Book(int bookId, String name, String author, double price) {

		this.bookId = bookId;
		this.name = name;
		this.author = author;
		this.price = price;

	}
	
	public Book() {
		
	}

	@Override
	public String toString() {
		return "Book: " + this.name + " Author: " + this.author + " Price: " + this.price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
}