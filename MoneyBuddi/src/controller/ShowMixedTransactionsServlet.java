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
import exceptions.InvalidDataException;
import model.Transaction;
import model.User;

/**
 * Servlet implementation class ShowMixedTransactionsServlet
 */
@WebServlet("/showMixed")
public class ShowMixedTransactionsServlet extends HttpServlet {
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			User u=(User) request.getSession().getAttribute("user");
			ArrayList<Transaction> transactions=TransactionDao.getInstance().getAllTransactionsByUser(u);
			request.setAttribute("transactions", transactions);
			request.getRequestDispatcher("showMixedTransactions.jsp").forward(request,response);
			
		} catch (SQLException | InvalidDataException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				int days= Integer.parseInt(request.getParameter("filter"));
				User user=(User) request.getSession().getAttribute("user");
				ArrayList<Transaction> transactions=TransactionDao.getInstance().getAllTransactionsByUserFiltered(user, days);
				request.setAttribute("transactions", transactions);
				request.getRequestDispatcher("showMixedTransactions.jsp").forward(request, response);;
			} catch (SQLException | InvalidDataException e) {
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
	
		
		
	}

}
