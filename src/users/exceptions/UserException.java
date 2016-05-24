package users.exceptions;

import users.datatypes.RequestType;
import files.datatypes.FilePath;
import files.exceptions.FileException;

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
		
		/**
		 * The user requested.
		 */
		private final String userId;

		/**
		 * @return the userId
		 */
		public String getUserId() {
			return userId;
		}

		public UnknownUserException(final String userId) {
			super();
			this.userId = userId;
		}

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
		
		private final String userId;

		public UserAlreadyExists(final String userId) {
			super();
			this.userId = userId;
		}

		/**
		 * @return the userId
		 */
		public String getUserId() {
			return userId;
		}
		
	}
	
	/**
	 * Thrown when a user sends a request without having
	 * the privileges to do so.
	 * The filePath indicates over which project/file the user was attempting
	 * the operation. If null, then the operation was over the whole database.
	 * @author Jaime
	 *
	 */
	public static class InvalidRequest extends FileException{
		/**
		 * Action we were trying to perform.
		 */
		private final RequestType request;
		
		/**
		 * Over what file/project we where trying to perform the operation.
		 * If null, it refers to the whole database.
		 */
		private final FilePath project;
		
		public InvalidRequest(final RequestType create, final FilePath project) {
			super();
			this.request = create;
			this.project = project;
		}
		
		public InvalidRequest(final RequestType request) {
			super();
			this.request = request;
			this.project = null;
		}
		
		/**
		 * @return the request
		 */
		public RequestType getRequest() {
			return request;
		}

		/**
		 * @return the project
		 */
		public FilePath getProject() {
			return project;
		}

		private static final long serialVersionUID = 1L;
		
	}

}