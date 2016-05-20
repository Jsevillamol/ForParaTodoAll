package users;

import java.util.List;

import files.datatypes.FilePath;
import users.datatypes.LoginInfo;
import users.datatypes.RequestType;
import users.datatypes.User;
import users.datatypes.UserLevel;
import users.exceptions.UserException.UnknownUserException;
import users.subsystems.IUserDAO;
import users.subsystems.SessionManager;
import users.subsystems.UserDAO;

/*
 * Singleton facade which builds the whole subsystem and offers its functionality
 * to other subsystems and controllers.
 * 
 * Implements two factory methods which return a reference to the singleton
 * as one of the interfaces it implements.
 */
public class UserMain implements UserInternalService, UserExternalService {
	
	/*
	 * Manages the creation, association and deletion of sessions.
	 */
	SessionManager sessionManager = SessionManager.getReference();
	
	/*
	 * Manages the user database. 
	 * Creates and stores Users.
	 */
	IUserDAO userDAO = UserDAO.getReference();
	
	/*
	 * Constructor is private, as this is a singleton.
	 */
	private UserMain(){}
	
	private static UserMain singleton = null;
	
	/*
	 * Singleton factory.
	 * Returns a reference to the singleton.
	 * If there is no reference yet, creates it.
	 */
	private static UserMain getReference(){
		if(singleton == null){
			singleton = new UserMain();
			return singleton;
		} else return singleton;
	}
	
	/*
	 * Factory method for the singleton as a UserInternalService
	 */
	public static UserInternalService getUserInternalService(){
		return getReference();
	}
	
	/*
	 * Factory method for the singleton as a UserInternalService
	 */
	public static UserExternalService getUserExternalService(){
		return getReference();
	}
	
	
	/*
	 * UserExternalService interface methods
	 */
	
	@Override
	public int login(LoginInfo loginInfo) throws UnknownUserException {
		User user; int sessionId = -1;
		
		user = userDAO.getUser(loginInfo.userId);
		
		if (user.checkPassword(loginInfo.password)){
			sessionId = sessionManager.generateSession(loginInfo.userId);
		}
		
		return sessionId;
	}

	@Override
	public boolean changeLoginInfo(int sessionId, LoginInfo newInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<FilePath> getProjects(int sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createUser(int sessionId, LoginInfo newUserInfo,
			UserLevel newUserLevel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addUserToProject(int sessionId, String userId,
			FilePath project) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeLevel(int sessionId, String user, UserLevel newLevel) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*
	 * UserInternalService interface methods
	 */
	
	@Override
	public boolean validateRequest(int sessionId, RequestType request,
			FilePath filePath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String identify(int SessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sudoAddUserToProject(String UserId, FilePath project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReferences(FilePath project) {
		// TODO Auto-generated method stub
		
	}

}
