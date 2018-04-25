package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDao;
import dao.TransactionDao;
import model.Account;
import model.Category;
import model.Transaction;
import model.User;

@WebServlet("/charts")
public class ChartsServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get all transactions for current month
		User u=(User)request.getSession().getAttribute("user");

		try {
			//get transactions
			ArrayList<Transaction> incomeTransactionsByMonth=TransactionDao.getInstance().getIncomeForMonth(u);
			ArrayList<Transaction> expenseTransactionsByMonth=TransactionDao.getInstance().getExpenseForMonth(u);
			System.out.println("income size "+incomeTransactionsByMonth.size());
			System.out.println("expense size "+expenseTransactionsByMonth.size());
			//create map category -> amount
			Map<Category,Double> incomeByMonth=new HashMap<>();
			Map<Category,Double> expenseByMonth=new HashMap<>();
			//iterate income array and add all to map, when key is already seen, increase amount
			
			//create two variables to hold total amount of income and expense and pass them to request
			double totalIncomeAmount=0;
			double totalExpenseAmount=0;
			//income
			for(Transaction t : incomeTransactionsByMonth) {
				if(incomeByMonth.containsKey(t.getCategory())) {
					incomeByMonth.put(t.getCategory(), incomeByMonth.get(t.getCategory())+t.getAmount());
				}else {
					incomeByMonth.put(t.getCategory(), t.getAmount());
				}
				totalIncomeAmount+=t.getAmount();
			}
			//expense
			for(Transaction t : expenseTransactionsByMonth) {
				if(expenseByMonth.containsKey(t.getCategory())) {
					expenseByMonth.put(t.getCategory(), expenseByMonth.get(t.getCategory())+t.getAmount());
				}else {
					expenseByMonth.put(t.getCategory(), t.getAmount());
				}
				totalExpenseAmount+=t.getAmount();
			}
			//add maps to request and forward
		   request.setAttribute("incomeByMonth", incomeByMonth);
		   request.setAttribute("expenseByMonth", expenseByMonth);
		   request.setAttribute("totalIncomeAmount", totalIncomeAmount);
		   request.setAttribute("totalExpenseAmount", totalExpenseAmount);
		   request.getRequestDispatcher("charts.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
