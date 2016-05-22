package users.subsystems;

import java.util.List;

import users.datatypes.User;

/**
 * Controls access to the database of users.
 * See IUserDAO for method information.
 */
public class UserDAO implements IUserDAO {
	
	/**
	 * Creation of instances aside from singleton disallowed.
	 */
	private UserDAO(){};
	
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
	public User getUser(final String userId){
		return null;//TODO
	}
	
	@Override
	public void storeUser(final User user){
		//TODO
	}
	
	@Override
	public void deleteUser(final String userId) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<String> searchUsers(final String regex){
		return null; //TODO
	}
	
	@Override
	public void contains(final String userId) {
		// TODO Auto-generated method stub
	}

}
