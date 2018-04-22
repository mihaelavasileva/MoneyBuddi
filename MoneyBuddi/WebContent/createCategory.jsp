<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="model.Category" %>
    <%@ page import="model.Transaction.TransactionType" %>
    <%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create category</title>
</head>
<body>
<h1>Create a new Category</h1>

<div>
		<form action="createCategory" method="post">
			<input type="text" name="name" required><br>
			
			<select name="type" value="type" required><br>
				<% List<TransactionType> types=(List<TransactionType>)request.getAttribute("Types");
					for(TransactionType t:types){
				%>
				<option > <%= t.toString() %> </option>
				<%} %>
				
			</select>
			
			
			<input type="submit" value="create">
		</form>
		<form action="main.jsp">
    				<input type="submit" value="close" />
			</form>
	</div>
	
	
	<% if(request.getAttribute("Success")!=null){ Category cat=(Category)request.getAttribute("Success"); %>
	<h2>Congrats you created a new category <%=cat.getCategory() %> </h2>
     <%} %>
</body>
</html>