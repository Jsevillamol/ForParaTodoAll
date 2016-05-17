package users.exceptions;

public class UserException extends Exception{
	
	private static final long serialVersionUID = -7102677415418620551L;

	/*
	 * Exception thrown when a client sends a request with a sessionId
	 * not recognized by the session manager. 
	 */
	public static class SessionExpired extends UserException {
	
		private static final long serialVersionUID = 1567679843188755082L;
	
	}

}