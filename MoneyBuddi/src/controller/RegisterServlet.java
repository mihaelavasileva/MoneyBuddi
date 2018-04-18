package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import exceptions.InvalidDataException;
import model.User;
import security.BCrypt;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//get data
			String username = request.getParameter("username");
			String pass1 = request.getParameter("password1");
			String pass2 = request.getParameter("password2");
			String email = request.getParameter("email");
			int age = Integer.parseInt(request.getParameter("age"));
			//validate data
			if(username.isEmpty() || username.length() < 5) {
				throw new InvalidDataException("username must be at least 5 chars long");
			}
			if(!pass1.equals(pass2)) {
				throw new InvalidDataException("passwords missmatch");
			}
			if(email==null || email.isEmpty()) {
				throw new InvalidDataException("Email cannot be null or empty.");
			}
			if(age<14 || age>100) {
				throw new InvalidDataException("The age must be a number between 14 and 100.");
			}
			//create user
			pass1=BCrypt.hashpw(pass1, BCrypt.gensalt());
			User u = new User(username, pass1, email, age);
			//add to db
			UserDao.getInstance().saveUser(u);
			request.getSession().setAttribute("user", u);
			//forward to login OR main
			//TODO create main.jsp
			request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);
		}
		catch (InvalidDataException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		catch (Exception e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
