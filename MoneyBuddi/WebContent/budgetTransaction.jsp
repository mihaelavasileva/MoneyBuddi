<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Budget" %> 
<%@ page import="model.Account" %> 
<%@page import="java.util.ArrayList"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Budget transaction</title>
</head>
<body>
	<h1>Budget info</h1>
	<%Budget budget=(Budget)request.getSession().getAttribute("budget"); %>
	<p>Balance:<%=budget.getAmount()  %> </p>
	<p>Category:<%=budget.getCategory().getCategory()+"-"+budget.getCategory().getType().toString() %> </p>
	<p>Begin:<%=budget.getBeginDate() %> </p>
	<p>End:<%=budget.getEndDate() %> </p>
	<p>Currency:<%=budget.getCurrency().getType().toString() %>
	

	<div>
		<form action="budgetTransaction" method="POST">
			Amount:<input type="number" name="amount" required>  <br>
			Account:<select name="accountId">
					  <%ArrayList<Account> accounts=(ArrayList<Account>)request.getSession().getAttribute("accounts"); 
					  		long accountId=0;
					 	    for(Account a:accounts){
					   %>
					  <option value="<%=accountId=a.getId() %>"> <%=a.getName()+"-"+a.getBalance()+"-"+a.getCurrency().getType().toString() %></option>
					  	  <%} %>
			              
			 		</select>
			Date:<input type="date" name="date" required><br>
			
			
			<input type="submit"  value="save">
		</form>
		
		<input type="button" onclick="location.href='main.jsp'" value="close">
	</div>

</body>
</html>