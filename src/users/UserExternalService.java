package users;

import java.util.List;

import users.datatypes.LoginInfo;
import users.datatypes.UserLevel;
import users.exceptions.UserException.IncorrectPassword;
import users.exceptions.UserException.SessionExpired;
import users.exceptions.UserException.UnknownUserException;
import files.datatypes.FilePath;
import files.exceptions.FileException.InexistentProject;
import files.exceptions.FileException.InvalidRequest;

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
	 * Closes the session of a logged user.
	 * @param sessionId
	 */
	void logoff(int sessionId) throws SessionExpired;
	
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
	 * If requester given by sessionId has the privileges, deletes the user indicated by userId.
	 * @param sessionId
	 * @param userId
	 */
	void deleteUser(int sessionId, String userId) throws SessionExpired, InvalidRequest, UnknownUserException;
	
	/**
	 * Returns a list of project identifiers in which the user is a participant.
	 * @throws SessionExpired 
	 * @throws UnknownUserException 
	 */
	List<FilePath> getProjects(int sessionId) throws SessionExpired;
	
	/**
	 * Changes the info associated to an user.
	 */
	void changeLoginInfo(int sessionId, LoginInfo newInfo) throws SessionExpired;
	
	/**
	 * Changes the privileges of a user.
	 * @throws InvalidRequest 
	 * @throws SessionExpired 
	 */
	void changeLevel(int sessionId, String userId, UserLevel newLevel) throws UnknownUserException, SessionExpired, InvalidRequest;
	
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
