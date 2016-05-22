package users;

import java.util.List;

import users.datatypes.LoginInfo;
import users.datatypes.UserLevel;
import users.exceptions.UserException.SessionExpired;
import users.exceptions.UserException.UnknownUserException;
import files.datatypes.FilePath;

/**
 * Functionality the subsystem user offers to external controllers.
 */
public interface UserExternalService {
	/**
	 * Verifies that the loginInfo is correct, and if successful generated a SessionId
	 * which will be associated during a period of time to a user.
	 */
	int login(LoginInfo loginInfo) throws UnknownUserException;
	
	/**
	 * Adds a new user with the specified level to the database.
	 */
	void createUser(int sessionId, LoginInfo newUserInfo, UserLevel newUserLevel);
	
	/**
	 * Deletes the account of a logged user with the indicated sessionId.
	 * 
	 * @param sessionId
	 * @throws SessionExpired
	 */
	void deleteUser(int sessionId) throws SessionExpired;
	
	/**
	 * Returns a list of project identifiers in which the user is a participant.
	 */
	List<FilePath> getProjects(int sessionId);
	
	/**
	 * Changes the info associated to an user.
	 */
	void changeLoginInfo(int sessionId, LoginInfo newInfo) throws SessionExpired, UnknownUserException;
	
	/**
	 * Changes the privileges of a user.
	 */
	void changeLevel(int sessionId, String user, UserLevel newLevel) throws UnknownUserException;
	
	/**
	 * Add a new collaborator to a project.
	 */
	void addUserToProject(int sessionId, String userId, FilePath project) throws UnknownUserException, SessionExpired;
	
	/**
	 * Removes the privileges of a user to edit a project.
	 */
	void deleteUserFromProject(int sessionId, String userId, FilePath project)
			throws SessionExpired, UnknownUserException;
	
}
