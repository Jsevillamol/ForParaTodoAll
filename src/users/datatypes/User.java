package users.datatypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import files.datatypes.FilePath;

/**
 * Object which represents an user.
 * They are built by IUserDAOs and have simple functionality allowing modification
 * and checks.
 */
public class User {
	/**
	 * Identifier.
	 */
	String userId;
	
	/**
	 * Hashed password with salt for login checks.
	 */
	String hashedPassword;
	
	/**
	 * To obfuscate the hashing. Is unique of each user and generated
	 * randomly at user creation.
	 */
	String salt;
	
	/**
	 * Privileges of user.
	 */
	UserLevel userLevel;
	
	/**
	 * List of projects in which the user collaborates.
	 */
	Set<FilePath> projects;
	
	/**
	 * Builds an user. The salt field is automatically generated,
	 * and the projects are initialized empty.
	 */
	public User(final String userId, final String password, final UserLevel userLevel){
		this.userId = userId;
		this.salt = "42"; //TODO: randomize
		//password = hashPassword(password, this.salt)
		this.hashedPassword = password;
		this.userLevel = userLevel;
		this.projects = new TreeSet<FilePath>();
	}
	
	/**
	 * Returns true if the hashed password + salt 
	 * corresponds to the stored password.
	 */
	public boolean checkPassword(final String password){
		//password = hashPassword(password, this.salt)
		return this.hashedPassword.equals(password);
	}
	//Getters and setters
	public String getUserId() {
		return userId;
	}

	public UserLevel getUserLevel() {
		return userLevel;
	}

	public List<FilePath> getProjects() {
		final List<FilePath> res = new ArrayList<FilePath>(projects);
		return res;
	}
	/**
	 * Removes a project from a user
	 * @param project
	 */
	public void removeProject(final FilePath project){
		projects.remove(project);
	}
	
	/**
	 * Returns true if user is a collaborator in a certain project.
	 */
	public boolean isACollaborator(final FilePath project){
		return projects.contains(project);
	}
	
	/**
	 * Modifies the user id and password,
	 * and generates new random salt to increase 
	 * security.
	 * @param info
	 */
	public void changeInfo(final LoginInfo info){
		userId = info.userId;
		this.salt = "42"; //TODO: randomize
		//password = hashPassword(password, this.salt)
		hashedPassword = info.password;
	}
	
	/**
	 * Changes the privileges of the user.
	 * @param newLevel
	 */
	public void changeLevel(final UserLevel newLevel){
		userLevel=newLevel;
	}
	
	/**
	 * Adds the project to the relation of project in
	 * which the user is a collaborator with edit privileges.
	 * @param project
	 * @return
	 */
	public boolean addUserToProject(final FilePath project){
		projects.add(project);
		return true;
	}
}