<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Category"%>
<%@ page import="model.Currency" %>
 <%@page import="java.util.ArrayList"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Budget</title>
</head>
<body>
	<div>
		<form action="createBudget" method="POST">
			Name:<input type="text" name="name" required><br>
			Balance:<input id="number" type="number" name="amount" required><br>
			
			Currency:<select name="currencyId" value="type" required><br>
			<% ArrayList<Currency> currencies=(ArrayList<Currency>)request.getAttribute("currencies"); %>
				<% for(Currency c:currencies){ %>
				<option value="<%=c.getId() %>">  <%= c.getType().toString() %> </option>
				<%} %>
			</select><br>
			
			Category:<select name="categoryId" value="type" required><br>
			<% ArrayList<Category> categories=(ArrayList<Category>)request.getAttribute("categories"); %>
				<% for(Category c:categories){ %>
				<option value="<%=c.getId() %>"> <%=c.getCategory()+"-"+c.getType().toString() %></option>
				<%} %>
			</select><br>
			
			Begin:<input type="date" name="begin" required><br>
			End  :<input type="date" name="end" required><br><br>
			
			
			<input type="submit" value="create">
			<br>
			<br>
		</form>
		<form action="budget" method="GET">
    				<input type="submit" value="close" />
		</form>
	</div>

</body>
</html>