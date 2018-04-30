 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@page import="utils.ConnectionDb" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Statement" %>



<jsp:useBean id="calculatorView" scope="request" class= "java.CalculateDiscountModel">
	<jsp:setProperty name="order" property="*" />
</jsp:useBean>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
<title>Discount calculator</title>
</head>
<body>
<h1>Discount calculator</h1>

<form action="${pageContext.request.contextPath}/CalculateDiscountModel" method="post">">

<div class="form-group">
  <label for="customer">Select customer:</label>
  <select class="form-control" id="customerId" name="customerId">
<%
try {
	ConnectionDb conn = new ConnectionDb();	
	String sql = "SELECT * FROM customer limit 5";
	ResultSet rs = conn.ConnectDb(sql);
		
	while (rs.next()){
	%>
	<option
		id="<%=rs.getInt("customer_id") %>" 
		value="<%=rs.getInt("customer_id") %>"
		onchange="this.form.submit()"><%=rs.getString("name") %></option>
	<%
	}
	
	} catch (Exception ex) {
		System.err.println("Connection failed " + ex);
	}
	
%>
  </select>
</div>

<div class="form-group">
  <label for="customer">Select discount:</label>
  <select class="form-control" id="discount" name="discount">
  	<option value="0">Standard purchase</option>
  	<option value="10">Big purchase</option>
  	<option value="20">Large purchase</option>
  	<option value="30">First purchase</option>
  </select>
 </div>

  <div class="form-group">
    <label for="units">Units:</label>
    <input type="text" class="form-control" id="units" value="<jsp:getProperty property="order" name="units"/>">
  </div>
  
    <div class="form-group">
    <label for="units">Price(hidden):</label>
    <input type="text" class="form-control" id="price" value="<jsp:getProperty property="order" name="price"/>">
  </div>
  
  <div class="form-group">
    <label for="amount">Sum without discount:</label>
    <input type="text" class="form-control" id="amount" value="<jsp:getProperty property="order" name="amount"/>">
  </div>
  
    <div class="form-group">
    <label for="amount">Sum with discount:</label>
    <input type="text" class="form-control" id="total" value="<jsp:getProperty property="order" name="total"/>">
  </div>
  
  <button type="submit" class="btn btn-default">Calculate discount</button>
  
  </form>


</body>
</html>