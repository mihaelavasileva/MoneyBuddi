package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.manager.TransactionManager;
import dao.AccountDao;
import dao.BudgetDao;
import dao.TransactionDao;
import model.Account;
import model.Budget;
import model.Expense;
import model.Income;
import model.Transaction.TransactionType;
import model.User;


@WebServlet("/budgetTransaction")
public class BudgetTransaction extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			int budgetId=Integer.parseInt(request.getParameter("budgetId"));
			Budget budget =BudgetDao.getInstance().getBudgetById(budgetId);
			
			
			ArrayList<Account> accounts=AccountDao.getInstance().getAllAccountsForUser((User) request.getSession().getAttribute("user"));
			
			request.getSession().setAttribute("budget", budget);
			request.getSession().setAttribute("accounts", accounts);
			
			
			request.getRequestDispatcher("budgetTransaction.jsp").forward(request, response);
		    
		} catch (Exception e) {
			request.setAttribute("exception", e);
		}
		
	}
		
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		double amount=Double.parseDouble(request.getParameter("amount"));
		
		String strinDate=request.getParameter("date");
    	LocalDate date=LocalDate.parse(strinDate);
		
		int accountId=Integer.parseInt(request.getParameter("accountId"));
		System.out.println(accountId);
		Account account=AccountDao.getInstance().getAccountById(accountId);
		
		Budget budget=(Budget) request.getSession().getAttribute("budget");
		
		if(budget.getCategory().getType().equals(TransactionType.EXPENSE)) {
			Expense expense=new Expense(amount,budget.getCurrency(), account, date, budget.getCategory());
			//TransactionDao.getInstance().addTransaction(expense,budget);
			 TransactionManager.getInstance().addTransaction(expense, budget);
		}else if(budget.getCategory().getType().equals(TransactionType.INCOME)) {
			Income income=new Income(amount, budget.getCurrency(), account, date, budget.getCategory());
			//TransactionDao.getInstance().addTransaction(income,budget);
			TransactionManager.getInstance().addTransaction(income, budget);
		}
		  System.out.println("Success");
		  request.getRequestDispatcher("budget.jsp").forward(request, response);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
