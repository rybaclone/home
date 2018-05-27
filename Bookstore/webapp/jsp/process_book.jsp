<%@page import="utils.BookServiceImpl"%>
<%@page import="model.Book"%>
<%@page import="java.util.Enumeration" %>
<%@page import="java.lang.Object" %>

<%
	String action = "new"; //default value

	if (request.getParameter("do") != null) {
		action = request.getParameter("do");
	}
	
	String name = request.getParameter("name");
	String author = request.getParameter("author");
	double price = Double.valueOf(request.getParameter("price"));
		
	try {
		BookServiceImpl bs = new BookServiceImpl();

		switch (action) {
		case "new":
			if ( bs.getBook(name, author).getBookId() == 0)
			{
				bs.insertBook(name, author, price);
			}	else {
				Book book = bs.getBook(name, author);										
				String message = "The book was not found: " +book.toString();
				request.setAttribute("message", message);
				//TODO: Handler PrintWriter out = respose.getWriter();	
				//response.sendRedirect("msg.jsp");										
			}
			break;
		case "edit":
			int bookId = Integer.parseInt( request.getParameter("id"));
			bs.updateBook(bookId, name, author, price);
			break;
		default:
            throw new IllegalArgumentException(action+ " Process action nor found!" );
	}
		response.sendRedirect("index.jsp");
	} catch (Exception ex) {
		System.err.println("Something failed " + ex);
		response.sendRedirect("msg.jsp");
	}
%>