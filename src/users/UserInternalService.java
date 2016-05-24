package users;

import users.datatypes.RequestType;
import users.exceptions.UserException.SessionExpired;
import users.exceptions.UserException.UnknownUserException;
import files.datatypes.FilePath;
import files.exceptions.FileException.InexistentProject;

/**
 * API the subsystem users offers to other subsystems.
 */
public interface UserInternalService {
	/**
	 * Returns true if the user associated to sessionId has the necessary privileges
	 * to do a request such as indicated in the parameter in the project/folder/file
	 * represented by filePath.
	 * @throws SessionExpired 
	 * @throws UnknownUserException 
	 */
	boolean validateRequest(int sessionId, RequestType request, FilePath filePath) throws SessionExpired;
	
	/**
	 * Returns the userId associated with sessionId.
	 * Used when a new project is created.
	 * @throws SessionExpired 
	 */
	String identify(int sessionId) throws SessionExpired;
	
	/**
	 * Adds user to project bypassing checks.
	 * Used when a new project is created.
	 * @throws UnknownUserException 
	 * @throws InexistentProject 
	 */
	void sudoAddUserToProject(String userId, FilePath project) throws InexistentProject, UnknownUserException;
	
	/**
	 * Deletes all references to project.
	 * Used when a project is deleted.
	 * @throws UnknownUserException 
	 */
	void deleteReferences(FilePath project);
	
}
