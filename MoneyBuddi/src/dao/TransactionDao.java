package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import controller.manager.DBManager;
import exceptions.InvalidDataException;
import javafx.util.converter.LocalDateStringConverter;
import model.Account;
import model.Budget;
import model.Expense;
import model.Income;
import model.Transaction;
import model.Transaction.TransactionType;
import model.User;

public class TransactionDao implements ITransactionDao {

	private static TransactionDao instance;
	private Connection connection;

	public synchronized static TransactionDao getInstance() {
		if (instance == null) {
			instance = new TransactionDao();
		}
		return instance;
	}

	private TransactionDao() {
		connection = DBManager.getInstance().getConnection();
	}

	@Override

	public synchronized void addTransaction(Transaction transaction,Budget budget) throws SQLException {
		try {
			connection.setAutoCommit(false);

		PreparedStatement s = connection.prepareStatement(
				"INSERT INTO transactions (amount, date, currency_id,"
				+ "account_id, category_id,transaction_type_id) "
				+ "VALUES (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			
			s.setDouble(1, transaction.getAmount());
			s.setDate(2, transaction.getDate());
			s.setLong(3, transaction.getCurrency().getId());
			s.setLong(4, transaction.getAccount().getId());
			s.setLong(5, transaction.getCategory().getId());
			s.setInt(6, TransactionTypeDAO.getInstance().getIdByTranscationType(transaction.getType()));

			System.out.println("transaction type: " + transaction.getType());
			int rows = s.executeUpdate();
			if (rows == 0) {
				// if transaction is not inserted, throw exception
				throw new SQLException("Transaction was not inserted in DB.");
			}

			// retrieve transaction`s id
			ResultSet generatedKey = s.getGeneratedKeys();
			generatedKey.next();
			transaction.setId((int) generatedKey.getLong(1));

			Account acc = transaction.getAccount();
			AccountDao.getInstance().updateAccount(acc);
			if(budget!=null) {
			BudgetDao.getInstance().updateBudget(budget);
			}
			connection.commit();

			s.close();
			System.out.println("Transaction successfully added in DB");
		} catch (SQLException e) {
			connection.rollback();
			throw new SQLException("Srry transaction can't be executed");
		} finally {
			connection.setAutoCommit(true);
		}

	}
	
	
	@Override
	public void deleteTransaction(Transaction transaction) throws SQLException {
		PreparedStatement s = null;
		try {
			s = connection.prepareStatement("DELETE FROM transactions WHERE id=?");
			s.setLong(1, transaction.getId());
			s.executeUpdate();
		} catch (Exception e) {
			throw new SQLException("Could not delete transaction.");
		} finally {
			s.close();
		}

		System.out.println("Deleted transaction from DB. ");
	}

	@Override
	public void changeTransaction(Transaction transaction) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("UPDATE transactions SET amount=?, date=?,"
					+ "currency_id=?, account_id=?, category_id=?, transaction_type_id=? " + "WHERE id=?");
			ps.setDouble(1, transaction.getAmount());
			ps.setDate(2, transaction.getDate());
			ps.setLong(3, transaction.getCurrency().getId());
			ps.setLong(4, transaction.getAccount().getId());
			ps.setLong(5, transaction.getCategory().getId());
			ps.setInt(6, transaction.getType().getId());
			ps.setLong(7, transaction.getId());

			ps.executeUpdate();
		} catch (Exception e) {
			throw new SQLException("Could not update transaction.");
		} finally {
			ps.close();
		}

		System.out.println("Transaction updated.");
	}

	@Override
	public ArrayList<Transaction> getAllTransactionsByUser(User u) throws SQLException, InvalidDataException {
		ArrayList<Transaction> transactions = new ArrayList();
		try (PreparedStatement ps = connection.prepareStatement(
				"SELECT id,amount,date,currency_id,account_id,category_id," + "transaction_type_id FROM transactions "
						+ "Where account_id in(select id from accounts where user_id=?)")) {
			ps.setLong(1, u.getId());
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					if (TransactionTypeDAO.getInstance().getTypeById(rs.getInt("transaction_type_id"))
							.equals(TransactionType.EXPENSE)) {
						transactions.add(new Expense(rs.getLong("id"), rs.getDouble("amount"),
								CurrencyDAO.getInstance().getCurrencyById(rs.getLong("currency_id")),
								AccountDao.getInstance().getAccountById(rs.getLong("account_id")),
								rs.getDate("date").toLocalDate(),
								CategoryDAO.getInstance().getCategoryByID(rs.getLong("category_id"))));
					} else if (TransactionTypeDAO.getInstance().getTypeById(rs.getInt("transaction_type_id"))
							.equals(TransactionType.INCOME)) {
						transactions.add(new Income(rs.getLong("id"), rs.getDouble("amount"),
								CurrencyDAO.getInstance().getCurrencyById(rs.getLong("currency_id")),
								AccountDao.getInstance().getAccountById(rs.getLong("account_id")),
								rs.getDate("date").toLocalDate(),
								CategoryDAO.getInstance().getCategoryByID(rs.getLong("category_id"))));

					}
				}
			}

		}
		return transactions;
	}

	@Override
	public ArrayList<Transaction> getAllExpenseTransactions(User u) throws Exception {
		ArrayList<Transaction> transactions = new ArrayList();
		// the id of expense in my db is 2
		try (PreparedStatement ps = connection.prepareStatement(
				"SELECT id,amount,date,currency_id,account_id,category_id," + "transaction_type_id FROM transactions "
						+ "Where account_id in(select id from accounts where user_id=?)"
						+ "AND transaction_type_id=?")) {
			ps.setLong(1, u.getId());
			ps.setInt(2, 2);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					transactions.add(new Expense(rs.getLong("id"), rs.getDouble("amount"),
							CurrencyDAO.getInstance().getCurrencyById(rs.getLong("currency_id")),
							AccountDao.getInstance().getAccountById(rs.getLong("account_id")),
							rs.getDate("date").toLocalDate(),
							CategoryDAO.getInstance().getCategoryByID(rs.getLong("category_id"))));
				}
			}
		}
		return transactions;
	}

	@Override
	public ArrayList<Transaction> getAllIncomeTransactions(User u) throws Exception {
		ArrayList<Transaction> transactions = new ArrayList();
		// the id of income in my db is 1
		try (PreparedStatement ps = connection.prepareStatement(
				"SELECT id,amount,date,currency_id,account_id,category_id," + "transaction_type_id FROM transactions "
						+ "Where account_id in(select id from accounts where user_id=?)"
						+ "AND transaction_type_id=?")) {
			ps.setLong(1, u.getId());
			ps.setInt(2, 1);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					transactions.add(new Expense(rs.getLong("id"), rs.getDouble("amount"),
							CurrencyDAO.getInstance().getCurrencyById(rs.getLong("currency_id")),
							AccountDao.getInstance().getAccountById(rs.getLong("account_id")),
							rs.getDate("date").toLocalDate(),
							CategoryDAO.getInstance().getCategoryByID(rs.getLong("category_id"))));
				}
			}
		}
		return transactions;
	}

	@Override
	public ArrayList<Transaction> getAllTransactionsByUserFiltered(User u, int days)
			throws SQLException, InvalidDataException {
		LocalDate now = LocalDate.now();
		LocalDate begin = now.minusDays(days);// this will show only the transaction from the begin date to now
		ArrayList<Transaction> transactions = new ArrayList();
		try (PreparedStatement ps = connection.prepareStatement(
				"SELECT id,amount,date,currency_id,account_id,category_id," + "transaction_type_id FROM transactions "
						+ "Where date>? and account_id in(select id from accounts where user_id=?)")) {
			ps.setDate(1, Date.valueOf(begin));
			ps.setLong(2, u.getId());
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					if (TransactionTypeDAO.getInstance().getTypeById(rs.getInt("transaction_type_id"))
							.equals(TransactionType.EXPENSE)) {
						transactions.add(new Expense(rs.getLong("id"), rs.getDouble("amount"),
								CurrencyDAO.getInstance().getCurrencyById(rs.getLong("currency_id")),
								AccountDao.getInstance().getAccountById(rs.getLong("account_id")),
								rs.getDate("date").toLocalDate(),
								CategoryDAO.getInstance().getCategoryByID(rs.getLong("category_id"))));

					} else if (TransactionTypeDAO.getInstance().getTypeById(rs.getInt("transaction_type_id"))
							.equals(TransactionType.INCOME)) {
						transactions.add(new Income(rs.getLong("id"), rs.getDouble("amount"),
								CurrencyDAO.getInstance().getCurrencyById(rs.getLong("currency_id")),
								AccountDao.getInstance().getAccountById(rs.getLong("account_id")),
								rs.getDate("date").toLocalDate(),
								CategoryDAO.getInstance().getCategoryByID(rs.getLong("category_id"))));

					}
				}
			}

		}
		return transactions;
	}

	@Override
	public ArrayList<Transaction> getAllTransactionsByUserAndDate(User u, LocalDate date)
			throws SQLException, InvalidDataException {

		ArrayList<Transaction> transactions = new ArrayList();
		try (PreparedStatement ps = connection.prepareStatement(
				"SELECT id,amount,date,currency_id,account_id,category_id," + "transaction_type_id FROM transactions "
						+ "Where date=? and account_id in(select id from accounts where user_id=?)")) {
			ps.setDate(1, Date.valueOf(date));
			ps.setLong(2, u.getId());
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					if (TransactionTypeDAO.getInstance().getTypeById(rs.getInt("transaction_type_id"))
							.equals(TransactionType.EXPENSE)) {
						transactions.add(new Expense(rs.getLong("id"), rs.getDouble("amount"),
								CurrencyDAO.getInstance().getCurrencyById(rs.getLong("currency_id")),
								AccountDao.getInstance().getAccountById(rs.getLong("account_id")),
								rs.getDate("date").toLocalDate(),
								CategoryDAO.getInstance().getCategoryByID(rs.getLong("category_id"))));

					} else if (TransactionTypeDAO.getInstance().getTypeById(rs.getInt("transaction_type_id"))
							.equals(TransactionType.INCOME)) {
						transactions.add(new Income(rs.getInt("id"), rs.getDouble("amount"),
								CurrencyDAO.getInstance().getCurrencyById(rs.getLong("currency_id")),
								AccountDao.getInstance().getAccountById(rs.getLong("account_id")),
								rs.getDate("date").toLocalDate(),
								CategoryDAO.getInstance().getCategoryByID(rs.getLong("category_id"))));

					}
				}
			}

		}
		return transactions;
	}

	@Override
	public ArrayList<Transaction> getExpenseByUserForMonth(User u) throws Exception {
		ArrayList<Transaction> transactions = new ArrayList();
		LocalDate now=LocalDate.now();
		int year=now.getYear();
		int month=now.getMonthValue();
		try (PreparedStatement ps = connection.prepareStatement(
				"SELECT id,amount,date,currency_id,account_id,category_id,"
						+ "transaction_type_id FROM transactions "
						+ "Where (date BETWEEN ? AND ?)"
						+ "AND transaction_type_id=? "
						+ "AND account_id IN(SELECT id from accounts where user_id=?)")) {
			ps.setDate(1, Date.valueOf(year+"-"+month+"-1"));
			ps.setDate(2, Date.valueOf(LocalDate.now()));
			ps.setInt(3, 2);
			ps.setLong(4, u.getId());
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
						transactions.add(new Expense(rs.getInt("id"), rs.getDouble("amount"),
								CurrencyDAO.getInstance().getCurrencyById(rs.getLong("currency_id")),
								AccountDao.getInstance().getAccountById(rs.getLong("account_id")),
								rs.getDate("date").toLocalDate(),
								CategoryDAO.getInstance().getCategoryByID(rs.getLong("category_id"))));

				}
			}

		}
		return transactions;
	}

	@Override
	public ArrayList<Transaction> getIncomeByUserForMonth(User u) throws Exception {
		ArrayList<Transaction> transactions = new ArrayList();
		LocalDate now=LocalDate.now();
		int year=now.getYear();
		int month=now.getMonthValue();
		try (PreparedStatement ps = connection.prepareStatement(
				"SELECT id,amount,date,currency_id,account_id,category_id,"
						+ "transaction_type_id FROM transactions "
						+ "Where (date BETWEEN ? AND ?)"
						+ "AND transaction_type_id=? "
						+ "AND account_id IN(SELECT id from accounts where user_id=?)")) {
			ps.setDate(1, Date.valueOf(year+"-"+month+"-1"));
			ps.setDate(2, Date.valueOf(LocalDate.now()));
			ps.setInt(3, 1);
			ps.setLong(4, u.getId());
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
						transactions.add(new Income(rs.getInt("id"), rs.getDouble("amount"),
								CurrencyDAO.getInstance().getCurrencyById(rs.getInt("currency_id")),
								AccountDao.getInstance().getAccountById(rs.getInt("account_id")),
								rs.getDate("date").toLocalDate(),
								CategoryDAO.getInstance().getCategoryByID(rs.getInt("category_id"))));

				}
			}

		}
		return transactions;
	}

	
}
