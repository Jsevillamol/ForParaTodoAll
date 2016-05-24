package users.subsystems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import users.datatypes.User;
import users.exceptions.UserException.UnknownUserException;

/**
 * Controls access to the database of users.
 * See IUserDAO for method information.
 */
public class UserDAO implements IUserDAO {
	
	/*
	 * Note: the following classes are not intended for production,
	 * but for testing purposes.
	 * In a serious production environment, those classes would be replaced by an
	 * actual database.
	 */
	private Map<String, User> dataBase = new HashMap<String, User>();
	/**
	 * Creation of instances aside from singleton disallowed.
	 */
	private UserDAO(){	};
	
	private static UserDAO singleton = null;
	
	/**
	 * Factory method for the singleton
	 */
	public static synchronized IUserDAO getReference(){
		if(singleton == null){
			singleton = new UserDAO();
			return singleton;
		} else return singleton;
	}
	
	@Override
	public User getUser(final String userId) throws UnknownUserException{
		if(!dataBase.containsKey(userId))throw new UnknownUserException();
		else return dataBase.get(userId);
	}
	
	@Override
	public void storeUser(final User user){
		dataBase.put(user.getUserId(), user);
	}
	
	@Override
	public void deleteUser(final String userId) throws UnknownUserException {
		if(!dataBase.containsKey(userId))throw new UnknownUserException();
		else dataBase.remove(userId);
	}
	
	@Override
	public List<String> searchUsers(final String regex){
		/*User are supposed to be filter by a regular expression (regex)
		 * however, it won't be done in this project, the complete dataBase is returned;
		 */
		return new ArrayList<String>(dataBase.keySet());
	}
	
	@Override
	public boolean contains(final String userId) {
		return dataBase.containsKey(userId);
	}

}
