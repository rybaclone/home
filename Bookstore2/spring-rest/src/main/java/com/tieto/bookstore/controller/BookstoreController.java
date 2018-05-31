package com.tieto.bookstore.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tieto.bookstore.model.Book;
import com.tieto.bookstore.utils.BookServiceImpl;



@RestController
@RequestMapping(BookstoreController.BOOKSTORE_BASE_URI)
public class BookstoreController {
	
	public final static String BOOKSTORE_BASE_URI = "v2/bookstore";
	
	private int PAGE_SIZE = 50;
	
	BookServiceImpl bs= new BookServiceImpl();
		
	@RequestMapping(value="/", method =RequestMethod.GET)
	public String greeting() {
		return "Tieto bookstore v.0.2";
	}


	@RequestMapping(value="/book/{bookId}")	
	public Book getBook(@PathVariable final int bookId) {		
		Book book  = bs.getBook(bookId);
		return book;
	}
	
	@RequestMapping(value="/page/{pageId}")	
	public ArrayList<Book> getBookList(@PathVariable final int pageId){
		ArrayList<Book> list = bs.getBookList(pageId-1, PAGE_SIZE);		
		return list;
	}
	
//	@SuppressWarnings("unused") //Rest API
	@RequestMapping(value = "/books", produces = MediaType.APPLICATION_XML_VALUE, method = RequestMethod.GET)
	public String getAllEmployeesXML(Model model)
	{
	    model.addAttribute("books", bs.getBookList(0, PAGE_SIZE+ PAGE_SIZE)); //getBooksCollection());
	    return "xmlTemplate";
	}
	
	@SuppressWarnings("unused")//RestClient
	private static void getAllBooks() { 
		String uri = "";
		//final String uri = "http://localhost:9000/module/books";
		
		RestTemplate restTemplate = new RestTemplate();
	     
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	    ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
	     
	    System.out.println(result);
	}
	
	
}
