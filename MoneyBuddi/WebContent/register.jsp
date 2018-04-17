<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
	<form action="register" method="post">
		Username<input type=text name="username" required><br>
		Password<input type=password name="password1" required><br>
		Confirm Password<input type=password name="password2" required><br>
		Email<input type=email name="email" required><br>
		Age<input type=number name="age" required><br>
		<input type=submit value="Register">
		<a href="index.jsp">Login here</a>&nbsp;if you already have an account!
	</form>
</body>
</html>