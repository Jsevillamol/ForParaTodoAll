package users.datatypes;

import java.io.Serializable;

/*
 * Info used to login.
 */
public class LoginInfo implements Serializable {
	
	private static final long serialVersionUID = 2743767699254563606L;

	/*
	 * Identifier of user.
	 */
	public String userId;
	
	/*
	 * Encrypted password.
	 */
	public String password;
}
