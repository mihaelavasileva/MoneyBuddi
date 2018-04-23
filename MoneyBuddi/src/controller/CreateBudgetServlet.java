package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BudgetDao;
import dao.CategoryDAO;
import dao.CurrencyDAO;
import exceptions.InvalidDataException;
import model.Budget;
import model.Category;
import model.Currency;
import model.User;


@WebServlet("/createBudget")
public class CreateBudgetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	    try {
	    	User u=(User) request.getSession().getAttribute("user");
			ArrayList<Category>categories=(ArrayList<Category>) CategoryDAO.getInstance().getAllCategoriesByUser(u);
			ArrayList<Currency>currencies=(ArrayList<Currency>) CurrencyDAO.getInstance().getAllCurrencies();
			request.setAttribute("categories", categories);//loading categories to chose from
			request.setAttribute("currencies", currencies);//loading currencies to chose from
			request.getRequestDispatcher("createBudget.jsp").forward(request, response);
		} catch (SQLException | InvalidDataException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          try {
        	User user=(User) request.getSession().getAttribute("user");
        	
        	String name=request.getParameter("name");
        	Double amount=Double.parseDouble(request.getParameter("amount"));
        	
        	int currencyId=Integer.parseInt(request.getParameter("currencyId"));
        	Currency currency=CurrencyDAO.getInstance().getCurrencyById(currencyId);
        	
        	int categoryId=Integer.parseInt(request.getParameter("categoryId"));
        	Category category=CategoryDAO.getInstance().getCategoryByID(categoryId);
        	
        	String bDate=request.getParameter("begin");
        	LocalDate beginDate=LocalDate.parse(bDate);
        	
        	String eDate=request.getParameter("end");
        	LocalDate endDate=LocalDate.parse(eDate);
        	
        	
        	
        	Budget b=new Budget(category, amount,user, currency, beginDate, endDate);
        	BudgetDao.getInstance().addBudget(b);
        	request.getRequestDispatcher("main.jsp").forward(request, response);
        
        	
          }catch(Exception e ) {
        	  request.setAttribute("exception", e);
  			  request.getRequestDispatcher("error.jsp").forward(request, response);
          }

	}

}
