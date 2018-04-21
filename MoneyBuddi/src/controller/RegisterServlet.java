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
import util.UserValidator;
import util.security.BCrypt;

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
			if(UserValidator.validateRegisterParameters(username, pass1, pass2, email, age)) {//if parameters match certain criteria
				if(UserDao.getInstance().getUserByUsername(username)==null){//if username is free
					if(!UserDao.getInstance().checkIfEmailExists(email)) {//if email is free
			
			pass1=BCrypt.hashpw(pass1, BCrypt.gensalt());
			User u = new User(username, pass1, email, age);
			//add to db
			//synchronized
			UserDao.getInstance().saveUser(u);
			request.getSession().setAttribute("user", u);
			request.getSession().setAttribute("logged", true);
			//forward to login OR main
			//TODO create main.jsp
			request.getRequestDispatcher("main.jsp").forward(request, response);

			return;
					}
					throw new InvalidDataException("Email is already in use");
				}
				throw new InvalidDataException("Username is already taken");
			}
		}
		catch (InvalidDataException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		catch (Exception e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	
}
