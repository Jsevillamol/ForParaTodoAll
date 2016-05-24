package users;

import java.util.List;

import users.UserException.IncorrectPassword;
import users.UserException.SessionExpired;
import users.UserException.UnknownUserException;
import users.datatypes.LoginInfo;
import users.datatypes.RequestType;
import users.datatypes.User;
import users.datatypes.UserLevel;
import users.subsystems.IUserDAO;
import users.subsystems.SessionManager;
import users.subsystems.UserDAO;
import files.datatypes.FilePath;

/**
 * Singleton facade which builds the whole subsystem and offers its
 * functionality to other subsystems and controllers.
 * 
 * Implements two factory methods which return a reference to the singleton as
 * one of the interfaces it implements.
 */
public class UserMain implements UserInternalService, UserExternalService {

	/**
	 * Manages the creation, association and deletion of sessions.
	 */
	SessionManager sessionManager = SessionManager.getReference();

	/**
	 * Manages the user database. Creates and stores Users.
	 */
	IUserDAO userDAO = UserDAO.getReference();

	/**
	 * Constructor is private, as this is a singleton.
	 */
	private UserMain() {
	}

	private static UserMain singleton = null;

	/**
	 * Singleton factory. Returns a reference to the singleton. If there is no
	 * reference yet, creates it.
	 */
	private static synchronized UserMain getReference() {
		if (singleton == null) {
			singleton = new UserMain();
			return singleton;
		} else
			return singleton;
	}

	/**
	 * Factory method for the singleton as a UserInternalService
	 */
	public static UserInternalService getUserInternalService() {
		return getReference();
	}

	/**
	 * Factory method for the singleton as a UserInternalService
	 */
	public static UserExternalService getUserExternalService() {
		return getReference();
	}

	/*
	 * UserExternalService interface methods
	 */

	@Override
	public int login(final LoginInfo loginInfo) throws UnknownUserException, IncorrectPassword {
		User user;
		int sessionId = -1;

		user = userDAO.getUser(loginInfo.userId);

		if (user.checkPassword(loginInfo.password)) {
			sessionId = sessionManager.generateSession(loginInfo.userId);
		} else {
			throw new IncorrectPassword();
		}

		return sessionId;
	}

	@Override
	public void changeLoginInfo(final int sessionId, final LoginInfo newInfo)
			throws SessionExpired, UnknownUserException {

		final String stringUser = sessionManager.getUser(sessionId);
		final User user = userDAO.getUser(stringUser);
		user.changeInfo(newInfo);
	}

	@Override
	public List<FilePath> getProjects(final int sessionId) throws UnknownUserException, SessionExpired {
		return userDAO.getUser(sessionManager.getUser(sessionId)).getProjects();

	}

	@Override
	public void createUser(final int sessionId, final LoginInfo newUserInfo, final UserLevel newUserLevel) {
		final User newUser = new User(newUserInfo.userId, newUserInfo.password, newUserLevel);
		userDAO.storeUser(newUser);
	}

	@Override
	public void addUserToProject(final int sessionId, final String userId, final FilePath project)
			throws UnknownUserException, SessionExpired {

		final String stringUser = sessionManager.getUser(sessionId);
		final User user = userDAO.getUser(stringUser);
		user.addUserToProject(project);

	}

	@Override
	public void changeLevel(final int sessionId, final String user, final UserLevel newLevel)
			throws UnknownUserException {

		final User userAux = userDAO.getUser(user);
		userAux.changeLevel(newLevel);

	}

	@Override
	public void deleteUserFromProject(final int sessionId, final String userId, final FilePath project)
			throws SessionExpired, UnknownUserException {
		final String stringUser = sessionManager.getUser(sessionId);
		final User user = userDAO.getUser(stringUser);
		user.addUserToProject(project);
	}

	@Override
	public void deleteUser(final int sessionId) throws SessionExpired, UnknownUserException {
		final String stringUser = sessionManager.getUser(sessionId);

		userDAO.deleteUser(stringUser);

	}

	/*
	 * UserInternalService interface methods
	 */

	@Override
	public boolean validateRequest(final int sessionId, final RequestType request, final FilePath filePath)
			throws UnknownUserException, SessionExpired {
		final User user = userDAO.getUser(sessionManager.getUser(sessionId));
		switch (request) {
		case Create:
			if (user.getUserLevel() == UserLevel.ADMIN || user.getUserLevel() == UserLevel.PROJECTLEADER) {
				return true;
			} else {
				return false;
			}
		case Edit:
			if (user.getUserLevel() == UserLevel.ADMIN) {
				return true;
			} else if (user.isACollaborator(filePath)) {
				return true;
			} else {
				return false;
			}
		case Delete:
			if (user.getUserLevel() == UserLevel.ADMIN) {
				return true;
			} else if (user.getUserLevel() == UserLevel.ADMIN && user.isACollaborator(filePath)) {
				return true;
			} else {
				return false;
			}
		default:
			return false;
		}
	}

	@Override
	public String identify(final int sessionId) throws SessionExpired {
		return sessionManager.getUser(sessionId);
	}

	@Override
	public void sudoAddUserToProject(final String userId, final FilePath project) throws UnknownUserException {
		final User user = userDAO.getUser(userId);
		user.addUserToProject(project);
	}

	@Override
	public void deleteReferences(final FilePath project){
		final List<String> userList = userDAO.searchUsers(null);
		for(final String userId: userList){
			User user;
			try {
				user = userDAO.getUser(userId);
			} catch (final UnknownUserException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
			if(user.isACollaborator(project)){
				user.removeProject(project);
			}
		}
	}

}
