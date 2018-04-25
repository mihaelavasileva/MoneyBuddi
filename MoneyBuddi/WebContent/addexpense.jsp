<%@page import="dao.AccountDao"%>
<%@page import="model.Account"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Expense</title>
</head>
<body>
<h1>Expense</h1>
	<div>
		<form action="addexpense" method="post">
			<input type="number" name="amount" required><br>
			<select name="accountId" required>
				<%
				User u=(User)request.getSession().getAttribute("user");
				ArrayList<Account> accounts=AccountDao.getInstance().getAllAccountsForUser(u);
				long accountId=0;
				for(Account a: accounts){
				%>
		    		<option value="<%= accountId=a.getId() %>"><%= a.getName() %></option>
				<%} 
				%>
			</select>
			<select name="categoryId" value="choose category" required><br>
				<% List<Category> categories=(List<Category>)request.getAttribute("categories");
					for(Category c : categories){
				%>
				<option value="<%=c.getId() %>"> <%= c.getCategory() %> </option>
				<%} %>
			</select>
			<input type="button" onclick="location.href='main.jsp'" value="close">
			<input type="submit" onclick="location.href='addexpense'" value="save">
		</form>
	</div>
</body>
</html>