package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TransactionDao;
import model.Income;
import model.Transaction;
import model.User;

@WebServlet("/showIncome")
public class ShowIncomeServlet extends HttpServlet {
  @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  try {
		//get all incomes by year, month or week (default - by week) for current user
		  User user=(User)request.getSession().getAttribute("user");
		  ArrayList<Transaction> incomeTransactions=TransactionDao.getInstance().getAllIncomeTransactions(user);
		  //add them to request and forward it
		  request.setAttribute("incomeTransactions", incomeTransactions);
		  request.getRequestDispatcher("showIncomeTransactions.jsp").forward(request, response);
	  }catch(Exception e) {
		  request.setAttribute("exception", e);
		  request.getRequestDispatcher("error.jsp").forward(request, response);
	  }
	}
}
