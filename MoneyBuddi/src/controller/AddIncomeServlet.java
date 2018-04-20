package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import dao.UserDao;
import model.Category;
import model.Transaction.TransactionType;
import model.User;

@WebServlet("/addincome")
public class AddIncomeServlet extends HttpServlet {
   
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//retrieve all categories from db and pass them to the request
		try {
			//get all categories
			//get the user id from session and then retrieve from db by that id
			User user=(User)request.getSession().getAttribute("user");
			int userId=user.getId();
			List<Category> categories = CategoryDAO.getInstance()
					.getAllCategoriesByUserAndType(UserDao.getInstance().getUserById(userId), TransactionType.INCOME);
			//add them to request
			request.setAttribute("categories", categories);
			//forward to addincome.jsp
			request.getRequestDispatcher("addincome.jsp").forward(request, response);
		}catch(Exception e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
