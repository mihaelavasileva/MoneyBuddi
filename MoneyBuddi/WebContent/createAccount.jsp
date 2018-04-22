<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="model.Category" %>
    <%@ page import="model.Currency" %>
    <%@ page import="model.Account" %>
    <%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create account</title>
</head>
<body>
<h1>Create new Account</h1>

<div>
		<form action="createAccount" method="post">
			Name:<input type="text" name="name" required><br>
			Balance:<input id="number" type="number" name="amount" required><br>
			
			Currency:<select name="currencyId" value="type" required><br>
			<% List<Currency> currencies=(List<Currency>)request.getAttribute("currencies"); %>
				<% for(Currency c:currencies){ %>
				<option value="<%=c.getId() %>">  <%= c.getType().toString() %> </option>
				<%} %>
			</select>
			
			
			<input type="submit" value="create">
		</form>
		<form action="main.jsp">
    				<input type="submit" value="close" />
			</form>
	</div>
	
	
	<% if(request.getAttribute("Success")!=null){ Account acc=(Account)request.getAttribute("Success"); %>
	<h2>Congrats you created a new Account <%=acc.getName() %> </h2>
     <%} %>


</body>
</html>