package users;

import java.util.List;

import files.datatypes.FilePath;
import files.exceptions.FileException.InexistentProject;
import files.exceptions.FileException.InvalidRequest;
import users.datatypes.LoginInfo;
import users.datatypes.UserLevel;
import users.exceptions.UserException.IncorrectPassword;
import users.exceptions.UserException.SessionExpired;
import users.exceptions.UserException.UnknownUserException;

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
	 * @throws SessionExpired 
	 * @throws UnknownUserException 
	 * @throws InvalidRequest 
	 */
	void createUser(int sessionId, LoginInfo newUserInfo, UserLevel newUserLevel) throws UnknownUserException, SessionExpired, InvalidRequest;
	
	/**
	 * Deletes the account of a logged user with the indicated sessionId.
	 * 
	 * @param sessionId
	 * @throws SessionExpired
	 * @throws UnknownUserException 
	 * @throws InvalidRequest 
	 */
	void deleteUser(int sessionId) throws SessionExpired, UnknownUserException, InvalidRequest;
	
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
	 * @throws InvalidRequest 
	 * @throws SessionExpired 
	 */
	void changeLevel(int sessionId, String user, UserLevel newLevel) throws UnknownUserException, SessionExpired, InvalidRequest;
	
	/**
	 * Add a new collaborator to a project.
	 * @throws InexistentProject 
	 * @throws InvalidRequest 
	 */
	void addUserToProject(int sessionId, String userId, FilePath project) throws UnknownUserException, SessionExpired, InexistentProject, InvalidRequest;
	
	/**
	 * Removes the privileges of a user to edit a project.
	 * @throws InexistentProject 
	 * @throws InvalidRequest 
	 */
	void deleteUserFromProject(int sessionId, String userId, FilePath project)
			throws SessionExpired, UnknownUserException, InexistentProject, InvalidRequest;
	
}
