package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDao;
import dao.CategoryDAO;
import dao.CurrencyDAO;
import exceptions.InvalidDataException;
import model.Account;
import model.Category;
import model.Currency;
import model.Currency.CurrencyType;
import model.User;
import model.Transaction.TransactionType;


@WebServlet("/createAccount")
public class CreateAccountServlet extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
			List<Currency> currencies=(List<Currency>) CurrencyDAO.getInstance().getAllCurrencies();
			request.setAttribute("currencies", currencies);
			request.getRequestDispatcher("createAccount.jsp").forward(request,response);
			
		} catch (SQLException e) {
		//
            request.getRequestDispatcher("error.jsp").forward(request, response);
         
		}
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			
			
			List<Currency> currencies=(List<Currency>) CurrencyDAO.getInstance().getAllCurrencies(); 
			request.setAttribute("currencies", currencies);         //loading categories again                                
																	//because the user stays on the same page 
																    //after pressing create 
			
			String name=request.getParameter("name");
			int currencyId=Integer.parseInt(request.getParameter("currencyId"));;
			String amount=request.getParameter("amount");
			double value=Double.parseDouble(amount);
			
			User user=(User)request.getSession().getAttribute("user");
		
			Currency c=CurrencyDAO.getInstance().getCurrencyById(currencyId);
			
			Account account=new Account(name,value,user,c);
			AccountDao.getInstance().addAccount(account);
			request.setAttribute("Success", account);
			
			request.getRequestDispatcher("createAccount.jsp").forward(request, response);
		}catch(Exception e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	
		
	}

}
