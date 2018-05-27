<%@page import="utils.BookServiceImpl"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="model.Book"%>
<%@page import="java.util.ArrayList"%>

<jsp:include page="header.jsp" />

<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-12">
				<h1>Bookstore Tieto v.01</h1>
			</div>
		</div>

		<div class="row">
			<div class="col-3">
				<div class="btn btn-outline-info btn-sm">
					<a href="form_book.jsp">New book</a>
				</div>
			</div>
			<div class="col-9">

				<%
					int PAGESIZE = 50;
					int currentPage = 0;
					if (request.getParameter("page") != null) {
						currentPage = Integer.parseInt(request.getParameter("page"));
					}

					BookServiceImpl bs = new BookServiceImpl();
					try {

						ArrayList<Book> lstBooks = bs.getBookList(currentPage, PAGESIZE);
				%>
				<h3>Book list</h3>

				<ul>
					<%
						for (Book book : lstBooks) {
					%>

					<li><%=book.toString()%>
						<div class="btn btn-outline-secondary btn-sm">
							<a href="form_book.jsp?do=edit&id=<%=book.getBookId()%>">Edit</a>
						</div></li>
					<%
						}
						} catch (Exception ex) {
							System.err.println("Something failed " + ex);
						}
					%>
				</ul>

				<div class="btn-toolbar" role="toolbar"
					aria-label="toolbar with button groups">
					<div class="btn-group mr-2" role="group" aria-label="Pages group">
						<%
							int numberOfBooks = bs.getNumberOfBooks();
							int p = 0;
							for (int i = 0; i < numberOfBooks; i += PAGESIZE) {
						%>

						<div class="btn btn-outline-secondary btn-sm">
							<a href="index.jsp?page=<%=p%>"><%=p + 1%></a>
						</div>
						<%
							p++;
							}
						%>
					</div>
				</div>

			</div>

		</div>
	</div>
</body>
</html>