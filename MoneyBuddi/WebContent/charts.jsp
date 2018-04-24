<%@page import="java.text.DecimalFormat"%>
<%@page import="model.Category"%>
<%@page import="java.util.Map"%>
<%@page import="model.Transaction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Charts</title>
</head>
<body>
<h1><%= LocalDate.now().getMonth()%><%= LocalDate.now().getYear() %></h1>
	<div>
		<div>
		<h2>Income details | </h2>
		<table style="width:50%">
	  <tr>
    	 <th>Category</th>
   		 <th>Amount</th>
   		 <th>%</th>
       </tr>
	  <% 
	  Map<Category,Double> incomeByMonth=(Map<Category,Double>)request.getAttribute("incomeByMonth");
	  double totalAmountIncome=0;
	  int transactionsCountIncome=0;
	  for(Map.Entry<Category,Double> e : incomeByMonth.entrySet()){
	 	totalAmountIncome+=e.getValue();
	 	transactionsCountIncome++;
			%>
			<tr>
	   			 <td><%= e.getKey().getCategory() %></td>
	    		 <td><%= e.getValue() %></td>
	    		 <% 
	    		 double percentsIncome=(e.getValue())*100/(double)request.getAttribute("totalIncomeAmount");
	    		 DecimalFormat format_2Places = new DecimalFormat("0.00");
	    		 percentsIncome = Double.valueOf(format_2Places.format(percentsIncome));
	    		 %>
				 <td><%= percentsIncome%></td>
	  		</tr>
			<%} %>
	  
	</table>

	  <h3>Total : <%= totalAmountIncome %></h3>
	  <h3>Number of transactions : <%= transactionsCountIncome %></h3>
		</div>
	</div>
	
	<div>
		<div>
		<h2>Expense details | </h2>
		
		<table style="width:50%">
	  <tr>
    	 <th>Category</th>
   		 <th>Amount</th>
   		 <th>%</th>
       </tr>
	  <% 
	  Map<Category,Double> expenseByMonth=(Map<Category,Double>)request.getAttribute("expenseByMonth");
	  double totalAmountExpense=0;
	  int transactionsCountExpense=0;
	  for(Map.Entry<Category,Double> e : expenseByMonth.entrySet()){
		  totalAmountExpense+=e.getValue();
		  transactionsCountExpense++;
			%>
			<tr>
	   			 <td><%= e.getKey().getCategory() %></td>
	    		 <td><%= e.getValue() %></td>
	    		 <% 
	    		 double percentsExpense=(e.getValue())*100/(double)request.getAttribute("totalExpenseAmount");
	    		 DecimalFormat format_2Places = new DecimalFormat("0.00");
	    		 percentsExpense = Double.valueOf(format_2Places.format(percentsExpense));
	    		 %>
				 <td><%= percentsExpense%></td>
	  		</tr>
			<%} %>
	  
	</table>

	  <h3>Total : <%= totalAmountExpense %></h3>
	  <h3>Number of transactions : <%= transactionsCountExpense %></h3>
		
		</div>
	</div>

</body>
</html>