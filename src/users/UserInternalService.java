package users;

import files.datatypes.FilePath;
import users.datatypes.RequestType;
import users.datatypes.User;

/*
 * API the subsystem users offers to other subsystems.
 */
public interface UserInternalService {
	/*
	 * Returns true if the user associated to sessionId has the necessary privileges
	 * to do a request such as indicated in the parameter in the project/folder/file
	 * represented by filePath.
	 */
	boolean validateRequest(int sessionId, RequestType request, FilePath filePath);
	
	/*
	 * Returns the userId associated with sessionId.
	 * Used when a new project is created.
	 */
	String identify(int SessionId);
	
	/*
	 * Adds user to project bypassing checks.
	 * Used when a new project is created.
	 */
	void sudoAddUserToProject(String UserId, FilePath project);
	
	/*
	 * Deletes all references to project.
	 * Used when a project is deleted.
	 */
	void deleteReferences(FilePath project);
	
}
