<jsp:include page="header.jsp" />

<title>Info, Error, Warning</title>
</head>
<body>
<h3>Message</h3>

<%
	String msg = request.getParameter("message");
%>
<p>Message: <%=msg %></p>
<p>Message2: ${param.message}</p>

</body>
</html>