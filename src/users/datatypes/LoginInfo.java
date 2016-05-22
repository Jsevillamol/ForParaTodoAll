package users.datatypes;

import java.io.Serializable;

/**
 * Info used to login.
 */
public class LoginInfo implements Serializable {
	
	private static final long serialVersionUID = 2743767699254563606L;

	/**
	 * Identifier of user.
	 */
	public String userId;
	
	/**
	 * Password in plain text.
	 */
	public String password;
}
