package users.subsystems;

import java.util.List;

import users.datatypes.User;

/*
 * Controls access to the user database
 */
public interface IUserDAO {
	/*
	 * Builds and returns a User with name userId
	 */
	public abstract User getUser(String userId);

	/*
	 * Stores a User in the database.
	 * If the user already existed, their information is overwritten.
	 */
	public abstract void storeUser(User user);
	
	/*
	 * True iff there was already a user with name userId in the
	 * database.
	 */
	public boolean contains(String userId);
	
	/*
	 * Returns a list of userId matching the regex.
	 */
	public abstract List<String> searchUsers(String regex);

}