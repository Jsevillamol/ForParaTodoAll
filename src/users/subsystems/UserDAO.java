package users.subsystems;

import java.util.List;

import users.datatypes.User;

/*
 * Controls access to the database of users.
 */
public class UserDAO implements IUserDAO {
	
	/*
	 * Creation of instances aside from singleton disallowed.
	 */
	private UserDAO(){};
	
	private static UserDAO singleton = null;
	
	/*
	 * Factory method for the singleton
	 */
	public static IUserDAO getReference(){
		if(singleton == null){
			singleton = new UserDAO();
			return singleton;
		} else return singleton;
	}
	
	@Override
	public User getUser(String userId){
		return null;//TODO
	}
	
	@Override
	public void storeUser(User user){
		//TODO
	}
	
	@Override
	public List<String> searchUsers(String regex){
		return null; //TODO
	}
	
	@Override
	public boolean contains(String userId) {
		// TODO Auto-generated method stub
		return false;
	}
}
