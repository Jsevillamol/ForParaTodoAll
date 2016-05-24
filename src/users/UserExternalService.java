package users;

import java.util.List;

import users.datatypes.LoginInfo;
import users.datatypes.UserLevel;
import users.exceptions.UserException.IncorrectPassword;
import users.exceptions.UserException.SessionExpired;
import users.exceptions.UserException.UnknownUserException;
import files.datatypes.FilePath;
import files.exceptions.FileException.InexistentProject;

/**
 * Functionality the subsystem user offers to external controllers.
 */
public interface UserExternalService {
	/**
	 * Verifies that the loginInfo is correct, and if successful generated a SessionId
	 * which will be associated during a period of time to a user.
	 * @throws IncorrectPassword 
	 */
	int login(LoginInfo loginInfo) throws UnknownUserException, IncorrectPassword;
	
	/**
	 * Adds a new user with the specified level to the database.
	 */
	void createUser(int sessionId, LoginInfo newUserInfo, UserLevel newUserLevel);
	
	/**
	 * Deletes the account of a logged user with the indicated sessionId.
	 * 
	 * @param sessionId
	 * @throws SessionExpired
	 * @throws UnknownUserException 
	 */
	void deleteUser(int sessionId) throws SessionExpired, UnknownUserException;
	
	/**
	 * Returns a list of project identifiers in which the user is a participant.
	 * @throws SessionExpired 
	 * @throws UnknownUserException 
	 */
	List<FilePath> getProjects(int sessionId) throws UnknownUserException, SessionExpired;
	
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
	 * @throws InexistentProject 
	 */
	void addUserToProject(int sessionId, String userId, FilePath project) throws UnknownUserException, SessionExpired, InexistentProject;
	
	/**
	 * Removes the privileges of a user to edit a project.
	 * @throws InexistentProject 
	 */
	void deleteUserFromProject(int sessionId, String userId, FilePath project)
			throws SessionExpired, UnknownUserException, InexistentProject;
	
}
