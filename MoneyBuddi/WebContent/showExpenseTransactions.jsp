<%@page import="model.Transaction"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Expense</title>
</head>
<body>

<h2>Expense</h2>

	<table style="width:50%">
	  <tr>
    	 <th>Category</th>
   		 <th>Amount</th>
       </tr>
	  <% 
	  ArrayList<Transaction> transactions=( ArrayList<Transaction>)request.getAttribute("expenseTransactions");
	  double totalAmount=0;
	  for(Transaction t : transactions){
		  totalAmount+=t.getAmount();
			%>
			<tr>
	   			 <td><%=t.getCategory().getCategory() %></td>
	    		 <td><%=t.getAmount() %></td>
	  		</tr>
			<%} %>
	  
	</table>

	  <h3>Total : <%= totalAmount %></h3>

</body>
</html>