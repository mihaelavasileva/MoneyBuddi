package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TransactionDao;
import model.Transaction;
import model.User;


@WebServlet("/showExpense")
public class ShowExpenseServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  try {
		//TODO get all incomes by year, month or week (default - by week) for current user
		  User user=(User)request.getSession().getAttribute("user");
		  int userId=user.getId();
		  ArrayList<Transaction> expenseTransactions=TransactionDao.getInstance().getAllExpenseTransactions(user);
		  //add them to request and forward it
		  request.setAttribute("expenseTransactions", expenseTransactions);
		  request.getRequestDispatcher("showExpenseTransactions.jsp").forward(request, response);
	  }catch(Exception e) {
		  request.getRequestDispatcher("error.jsp").forward(request, response);
	  }
	}
	
}