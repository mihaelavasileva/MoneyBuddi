package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import exceptions.InvalidDataException;
import model.User;
import security.BCrypt;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");			
			String pass = request.getParameter("password");
			
						
			User u = UserDao.getInstance().getUserByUsernameAndPassword(pass, username);
			
			if(u != null) {
				System.out.println(u.getPassword());
				request.getSession().setAttribute("user", u);
				//TODO create main.jsp
				request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);
			}
			else {	
				throw new InvalidDataException("invalid username or password");
			}
		}
		catch (InvalidDataException e) {
				request.setAttribute("exception", e);
				//TODO create error.jsp
				request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		catch (SQLException e) {
				request.setAttribute("exception", e);
				request.getRequestDispatcher("error.jsp").forward(request, response);
		}		
	}

}
