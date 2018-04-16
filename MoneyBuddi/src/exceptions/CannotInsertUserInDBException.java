package exceptions;

public class CannotInsertUserInDBException extends Exception {

	public CannotInsertUserInDBException(String message) {
		super(message);
	}
}
