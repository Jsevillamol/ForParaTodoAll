package users.datatypes;

import java.util.List;

import sharedtypes.FilePath;

/*
 * Object which represents an user.
 * They are built by IUserDAOs and have simple functionality allowing modification
 * and checks.
 */
public class User {
	String userId;
	
	/*
	 * Hashed password for login checks.
	 */
	String password;
	
	/*
	 * Privileges of user.
	 */
	UserLevel userLevel;
	
	/*
	 * List of projects in which the user collaborates.
	 */
	List<FilePath> projects;
	
	/*
	 * Returns true if the password corresponds to the stored password.
	 */
	public boolean checkPassword(String password){
		return this.password.equals(password);
	}

	public String getUserId() {
		return userId;
	}

	public UserLevel getUserLevel() {
		return userLevel;
	}

	public List<FilePath> getProjects() {
		return projects;
	}
	
	public void removeProject(FilePath project){
		projects.remove(project);
	}
}
