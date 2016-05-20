package users;

import java.util.List;

import files.datatypes.FilePath;
import users.datatypes.LoginInfo;
import users.datatypes.UserLevel;
import users.exceptions.UserException.UnknownUserException;

/*
 * Functionality the subsystem user offers to external controllers.
 */
public interface UserExternalService {
	/*
	 * Verifies that the loginInfo is correct, and if successful generated a SessionId
	 * which will be associated during a period of time to a user.
	 */
	int login(LoginInfo loginInfo) throws UnknownUserException;
	
	/*
	 * Changes the info associated to an user
	 */
	boolean changeLoginInfo(int sessionId, LoginInfo newInfo);
	
	/*
	 * Returns a list of project identifiers in which the user is a participant.
	 */
	List<FilePath> getProjects(int sessionId);
	
	/*
	 * Adds a new user with the specified level to the database.
	 */
	boolean createUser(int sessionId, LoginInfo newUserInfo, UserLevel newUserLevel);
	
	/*
	 * Add a new collaborator to a project
	 */
	boolean addUserToProject(int sessionId, String userId, FilePath project);
	
	/*
	 * Changes the level of a user.
	 */
	boolean changeLevel(int sessionId, String user, UserLevel newLevel);
	
	
	
}
