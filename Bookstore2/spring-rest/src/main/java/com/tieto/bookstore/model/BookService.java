package com.tieto.bookstore.model;

import java.util.ArrayList;

public interface BookService {
	
	
	ArrayList<Book> getBookList(int page, int limit);
	
	void updateBook(int id, String name, String author, double price);
	
	void insertBook(String name, String author, double price);
	
	Book getBook(int id);
	
	Book getBook(String name, String author);

	

}
