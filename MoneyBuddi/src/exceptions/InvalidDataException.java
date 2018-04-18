package exceptions;

public class InvalidDataException extends Exception {

	String message;
	public InvalidDataException(String message) {
		super(message);
	}
}
