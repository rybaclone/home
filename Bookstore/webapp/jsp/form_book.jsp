<%@page import="utils.BookServiceImpl"%>
<%@page import="model.Book"%>
<jsp:include page="header.jsp" />
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">

				<%
					String action = "new";
					Book book = new Book();
					if (request.getParameter("do") != null) {
						action = request.getParameter("do");
					}

					//init values
					int BookId;
					String bookName = "";
					String bookAuthor = "";
					int id = 0;

					if (request.getParameter("id") != null) {
						int bookId = Integer.parseInt(request.getParameter("id"));
						BookServiceImpl bs = new BookServiceImpl();
						book = bs.getBook(bookId);

						bookName = book.getName();
						bookAuthor = book.getAuthor();
						id = bookId;
					}
				%>

				<h3><%=action%>
					book
				</h3>

				<form method="post" action="process_book.jsp?do=<%=action%>">

					<div class="form-group">

						<input type="hidden" name="id" value="<%=id%>"> <label
							for="name">Book name:</label> <input type="text"
							class="form-control" id="name" placeholder="Enter name"
							name="name" value="<%=bookName%>"> <label for="author">Author:</label>
						<input type="text" class="form-control" id="author"
							placeholder="Enter author" name="author" value="<%=bookAuthor%>">


						<label for="price">Price:</label> <input type="text"
							class="form-control" id="price" placeholder="Enter price"
							name="price" value="<%=book.getPrice()%>">
					</div>

					<input class="btn btn-outline-info" type="submit" value="submit">
				</form>

			</div>
		</div>
	</div>
</body>
</html>