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
	 * Hashed password with salt for login checks.
	 */
	String hashedpassword;
	
	/*
	 * To obfuscate the hashing. Is unique of each user and generated
	 * randomly at user creation.
	 */
	String salt;
	
	/*
	 * Privileges of user.
	 */
	UserLevel userLevel;
	
	/*
	 * List of projects in which the user collaborates.
	 */
	List<FilePath> projects;
	
	/*
	 * Returns true if the hashed password + salt 
	 * corresponds to the stored password.
	 */
	public boolean checkPassword(String password){
		//password = hashPassword(password, this.salt)
		return this.hashedpassword.equals(password);
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
