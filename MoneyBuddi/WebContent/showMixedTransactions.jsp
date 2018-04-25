<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Transaction"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mixed Transactions</title>
</head>
<body>

	<a  href="main.jsp">Home</a>
	<div>
	   <form action="showMixed" method="post">
			<select name="filter" required>
				<%int filter=365; %>
    			<option value="<%=filter=1 %>"> 1-day </option>
    			<option value="<%=filter=7 %>"> 1-week</option>
    			<option value="<%=filter=30 %>"> 1-month </option>
    			<option value="<%=filter=365 %>"> 1-year </option>
    		
			</select>
			<input type="submit" value="Show">
		</form>
	</div>
	
	<div>
		<table style="width:30%">
  			<tr>
    			<th>Date</th>
    			<th>Category</th>
    			<th>type</th> 
    			<th>amount</th>
  			</tr>
 
  			<% for(Transaction t:(ArrayList<Transaction>)request.getAttribute("transactions")){%>
  			<tr>
			  	<td> <%=t.getDate() %> </td>
			    <td><%=t.getCategory().getCategory() %></td>
			    <td><%=t.getCategory().getType().toString() %></td>
			    <td><%=t.getAmount() %></td>
  			</tr>
  			<%} %>
		</table>
	
	</div>



</body>
</html>