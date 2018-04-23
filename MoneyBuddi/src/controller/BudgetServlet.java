package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BudgetDao;
import exceptions.InvalidDataException;
import model.Budget;
import model.User;


@WebServlet("/budget")
public class BudgetServlet extends HttpServlet {

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			User u=(User) request.getSession().getAttribute("user");
			ArrayList<Budget> budgets=(ArrayList<Budget>) BudgetDao.getInstance().getAllBudgetsForUser(u);
			request.getSession().setAttribute("budgets", budgets);
			request.getRequestDispatcher("budget.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	    

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
