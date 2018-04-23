<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="model.User"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="model.Budget"%>
    <%@page import="dao.BudgetDao"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Budgets</title>
</head>
<body>
	<div>
	  <h1>Your budgets</h1>
		<select name="budgetId" required>
		<%
		
		ArrayList<Budget> budgets=(ArrayList<Budget>)request.getSession().getAttribute("budgets");
		int budgetId=0;
		for(Budget b: budgets){
		%>
    		<option value="<%= budgetId=b.getId() %>"><%= b.getCategory().getCategory()+" "
    													 +b.getCategory().getType().toString()+" "
    													 +b.getAmount()%></option>
		<%} 
		request.getSession().setAttribute("budgetId", budgetId);
		%>
		</select><br>
		<br>
		
		
		
		
	</div>
	   	<button type="button" onclick="location.href='createBudget'" >Create Budget</button>
	<div>
	
	
	
	
	</div>


</body>
</html>