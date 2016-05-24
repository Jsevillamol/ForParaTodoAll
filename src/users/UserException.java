package users;

/**
 * Exceptions which occur in the User subsystem.
 * @author Jaime
 *
 */
public class UserException extends Exception{
	
	private static final long serialVersionUID = -7102677415418620551L;

	/**
	 * Exception thrown when a client sends a request with a sessionId
	 * not recognized by the session manager. 
	 */
	public static class SessionExpired extends UserException {
	
		private static final long serialVersionUID = 1567679843188755082L;
	
	}
	
	/**
	 * Exception thrown when a userId does not correspond to any user in
	 * the database.
	 */
	public static class UnknownUserException extends UserException {

		private static final long serialVersionUID = 1378175415964262311L;
		
	}
	
	/**
	 * Exception thrown when a password provided does not coincide
	 * after hashing with with the stored password.
	 * @author Jaime
	 *
	 */
	public static class IncorrectPassword extends UserException {

		private static final long serialVersionUID = 155628052654572898L;
		
	}
	
	/**
	 * Exception thrown when a userId already exists in the database
	 * when it is not expected to be.
	 * @author Jaime
	 *
	 */
	public static class UserAlreadyExists extends UserException {

		private static final long serialVersionUID = 1378175415964262311L;
		
	}
	
	/**
	 * Thrown when a user sends a request to Users without having the
	 * privileges to do so.
	 * @author Jaime
	 *
	 */
	public static class InvalidRequest extends UserException {

		private static final long serialVersionUID = -5467143102226475382L;
		
	}

}