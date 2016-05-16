package users.datatypes;

import java.util.List;

import sharedtypes.FilePath;

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
}
