package util;

import exceptions.InvalidDataException;

public class UserValidator {
	
	private static final String EMAIL_REGEX = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}";
	private static final int USERNAME_MIN_LENGTH=5;
	private static final String USERNAME_REGEX = "^[0-9a-zA-Z]{1,45}";
	private static final String PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$&+,:;=?@#|'<>.-^*()%!])(?=\\S+$).{8,45}";
	private static final int MIN_AGE=14;
	private static final int MAX_AGE=100;
	
	//PASSWORD
	//(?=.*[0-9]) a digit must occur at least once
	//(?=.*[a-z]) a lower case letter must occur at least once
	//(?=.*[A-Z]) an upper case letter must occur at least once
	//(?=.*[$&+,:;=?@#|'<>.-^*()%!]) a special character must occur at least once
	//(?=\\S+$) no whitespace allowed in the entire string
	//.{8,} at least 8 characters
	
	
	public static boolean validateRegisterParameters(String username,String password1,String password2,String email,int age) throws InvalidDataException {
		if(username.matches(USERNAME_REGEX)) {
			if(password1.equals(password2)) {
				if(password1.matches(PASSWORD_REGEX)) {
					if(email.matches(EMAIL_REGEX)) {
						if(age>=MIN_AGE && age<=MAX_AGE) {
							return true;
						}
					}
					throw new InvalidDataException("Srry invalid email format");	
				}
				throw new InvalidDataException("Srry password is not strong enough!");	
			}
			throw new InvalidDataException("Srry the second password does not match the first!");
		}
		throw new InvalidDataException("Sorry username does not match requirements!");
			
	}
	

}
