<%@page import="model.Transaction"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h2>Income</h2>

	<table style="width:50%">
	  <caption>Income</caption>
	  <tr>
    	 <th>Category</th>
   		 <th>Amount</th>
       </tr>
	  <% 
	  ArrayList<Transaction> transactions=( ArrayList<Transaction>)request.getAttribute("incomeTransactions");
	  for(Transaction t : transactions){
			%>
			<tr>
	   			 <td><%=t.getCategory() %></td>
	    		 <td><%=t.getAmount() %></td>
	  		</tr>
			<%} %>
	  
	</table>

</body>
</html>