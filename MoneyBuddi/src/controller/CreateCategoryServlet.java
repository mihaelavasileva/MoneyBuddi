package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import dao.TransactionTypeDAO;
import exceptions.InvalidDataException;
import model.Category;
import model.Transaction.TransactionType;
import model.User;


@WebServlet("/createCategory")
public class CreateCategoryServlet extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("name");
		String type=request.getParameter("type");
		
		
		TransactionType ttype=null;
		try {
			User user=(User)request.getSession().getAttribute("user");
			int userId=user.getId();
			try {
			ttype=TransactionType.valueOf(type);
			}catch(IllegalArgumentException e) {
				throw new InvalidDataException("Srry no such caregory");
			}
			Category category=new Category(name, ttype, userId);
			request.setAttribute("Success", category);
			CategoryDAO.getInstance().addCategory(category);
			request.getRequestDispatcher("createcategory.jsp").forward(request, response);
		}catch(Exception e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	
		
	}

}
