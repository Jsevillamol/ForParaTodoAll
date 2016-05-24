package users.subsystems;

import java.util.List;

import users.UserException.UnknownUserException;
import users.datatypes.User;

/**
 * Controls access to the user database.
 */
public interface IUserDAO {
	/**
	 * Builds and returns a User with name userId
	 */
	public User getUser(String userId) throws UnknownUserException;

	/**
	 * Stores a User in the database.
	 * If the user already existed, their information is overwritten.
	 */
	public void storeUser(User user);
	
	/**
	 * Deletes a user from the database.
	 * @throws UnknownUserException 
	 */
	public void deleteUser(String userId) throws UnknownUserException;
	
	/**
	 * True iff there was already a user with name userId in the
	 * database.
	 * @return 
	 */
	public boolean contains(String userId);
	
	/**
	 * Returns a list of userId matching the regex.
	 */
	public List<String> searchUsers(String regex);
	
}