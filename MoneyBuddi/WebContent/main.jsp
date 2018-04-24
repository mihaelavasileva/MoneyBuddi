<%@page import="java.util.ArrayList"%>
<%@page import="model.Account"%>
<%@page import="java.util.List"%>
<%@page import="dao.AccountDao"%>
<%@page import="model.User"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MoneyBuddi</title>
</head>
<body>
	<div>
		<h1><%= LocalDate.now() %></h1>

		
		<button type="button" onclick="location.href='createCategory'">New Category</button>
		
	</div>

	<div>
		<select name="accountId" required>
		<%
		User u=(User)request.getSession().getAttribute("user");
		ArrayList<Account> accounts=AccountDao.getInstance().getAllAccountsForUser(u);
		long accountId=0;
		for(Account a: accounts){
		%>
    		<option value="<%= accountId=a.getId() %>"><%= a.getName() %></option>
		<%} 
		request.getSession().setAttribute("accountId", accountId);
		%>
		</select>
			<button type="button" onclick="location.href='addincome'" style="color:lightgreen; background-color:green">Add Income</button>
			<button type="button" onclick="location.href='addexpense'" style="background-color:red;color:pink">Add Expense</button>
	</div>

	<div>
		<button type="button" onclick="location.href='showIncome'" style="color:lightgreen; background-color:green">Income</button>
		<button type="button" onclick="location.href='showExpense'" style="background-color:red;color:pink">Expense</button>
		<button type="button" onclick="location.href='showMixed'" style="background-color:#FF338A;color:black">Mixed</button>
		<button type="button" onclick="location.href='charts'"">Charts</button>
	</div><br>
	
	
	<div>
		<form action="createAccount" method="GET">
			<input type="submit" value="create new Account" />
		</form>
	</div><br>
	
	<div>
		<form action="budget" method="GET">
			<input type="submit" value="Budgets" />
		</form>
	</div>


</body>
</html>