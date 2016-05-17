package users.datatypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import sharedtypes.FilePath;

/*
 * Object which represents an user.
 * They are built by IUserDAOs and have simple functionality allowing modification
 * and checks.
 */
public class User {
	/*
	 * Identifier.
	 */
	String userId;
	
	/*
	 * Hashed password with salt for login checks.
	 */
	String hashedPassword;
	
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
	Set<FilePath> projects;
	
	/*
	 * Builds an user. The salt field is automatically generated,
	 * and the projects are initialized empty.
	 */
	public User(String userId, String password, UserLevel userLevel){
		this.userId = userId;
		this.salt = "42"; //TODO: randomize
		//password = hashPassword(password, this.salt)
		this.hashedPassword = password;
		this.userLevel = userLevel;
		this.projects = new TreeSet<FilePath>();
	}
	
	/*
	 * Returns true if the hashed password + salt 
	 * corresponds to the stored password.
	 */
	public boolean checkPassword(String password){
		//password = hashPassword(password, this.salt)
		return this.hashedPassword.equals(password);
	}

	public String getUserId() {
		return userId;
	}

	public UserLevel getUserLevel() {
		return userLevel;
	}

	public List<FilePath> getProjects() {
		List<FilePath> res = new ArrayList<>(projects);
		return res;
	}
	
	public void removeProject(FilePath project){
		projects.remove(project);
	}
	
	/*
	 * Returns true if user is a collaborator in a certain project.
	 */
	public boolean isACollaborator(FilePath project){
		return projects.contains(project);
	}
}
