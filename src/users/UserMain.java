package users;

import java.util.List;

import users.datatypes.LoginInfo;
import users.datatypes.RequestType;
import users.datatypes.User;
import users.datatypes.UserLevel;
import users.exceptions.UserException.IncorrectPassword;
import users.exceptions.UserException.SessionExpired;
import users.exceptions.UserException.UnknownUserException;
import users.exceptions.UserException.UserAlreadyExists;
import users.subsystems.IUserDAO;
import users.subsystems.SessionManager;
import users.subsystems.UserDAO;
import files.FileMain;
import files.FilesInternaService;
import files.datatypes.FilePath;
import files.exceptions.FileException.InexistentProject;
import files.exceptions.FileException.InvalidRequest;


/**
 * Singleton facade which builds the whole subsystem and offers its
 * functionality to other subsystems and controllers.
 * 
 * Implements two factory methods which return a reference to the singleton as
 * one of the interfaces it implements.
 */
public class UserMain implements UserInternalService, UserExternalService {
	
	/*
	 * Internal interfaces
	 */
	/**
	 * Allows interaction with the projects database.
	 */
	FilesInternaService fileSystem = FileMain.getInternalService();
	
	/*
	 * User Subsystems
	 */
	
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
	public int login(final LoginInfo loginInfo) 
			throws UnknownUserException, IncorrectPassword {
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
	public void logoff(final int sessionId) throws SessionExpired {
		sessionManager.closeSession(sessionId);
	}
	
	@Override
	public void changeLoginInfo(final int sessionId, final LoginInfo newInfo)
			throws SessionExpired {
		final String stringUser = sessionManager.getUser(sessionId);
		User user;
		try {
			user = userDAO.getUser(stringUser);
		} catch (final UnknownUserException e) {
			throw new RuntimeException(e);
		}
		user.changeInfo(newInfo);
		sessionManager.changeSession(sessionId, newInfo.userId);
		userDAO.storeUser(user);
	}

	@Override
	public List<FilePath> getProjects(final int sessionId) throws SessionExpired {
		try {
			return userDAO.getUser(sessionManager.getUser(sessionId)).getProjects();
		} catch (final UnknownUserException e) {
			//If the user is unknown our internal state is inconsistent.
			throw new RuntimeException(e);
		}
	}

	@Override
	public void createUser(final int sessionId, final LoginInfo newUserInfo, final UserLevel newUserLevel) 
			throws SessionExpired, InvalidRequest, UserAlreadyExists {
		if(!validateRequest(sessionId, RequestType.EDITUSER, null))
			throw new InvalidRequest(RequestType.EDITUSER, null);
		if(userDAO.contains(newUserInfo.userId))
			throw new UserAlreadyExists();
		final User newUser = new User(newUserInfo.userId, newUserInfo.password, newUserLevel);
		userDAO.storeUser(newUser);
	}

	@Override
	public void addUserToProject(final int sessionId, final String userId, final FilePath project)
			throws UnknownUserException, SessionExpired, InexistentProject, InvalidRequest {
		if(!fileSystem.existsProject(project))
			throw new InexistentProject(project);
		if(!validateRequest(sessionId, RequestType.MANAGEUSER, project))
			throw new InvalidRequest(RequestType.MANAGEUSER, project);
		final String stringUser = sessionManager.getUser(sessionId);
		final User user = userDAO.getUser(stringUser);
		user.addUserToProject(project);
		userDAO.storeUser(user);
	}

	@Override
	public void changeLevel(final int sessionId, final String user, final UserLevel newLevel)
			throws UnknownUserException, SessionExpired, InvalidRequest {
		if(!validateRequest(sessionId, RequestType.EDITUSER, null))
			throw new InvalidRequest(RequestType.EDITUSER, null);
		final User userAux = userDAO.getUser(user);
		userAux.changeLevel(newLevel);
		userDAO.storeUser(userAux);
	}

	@Override
	public void deleteUserFromProject(final int sessionId, final String userId, final FilePath project)
			throws SessionExpired, UnknownUserException, InexistentProject, InvalidRequest {
		if(!fileSystem.existsProject(project))
			throw new InexistentProject(project);
		if(!validateRequest(sessionId, RequestType.MANAGEUSER, project))
			throw new InvalidRequest(RequestType.MANAGEUSER, project);
		final String stringUser = sessionManager.getUser(sessionId);
		final User user = userDAO.getUser(stringUser);
		user.addUserToProject(project);
		userDAO.storeUser(user);
	}

	@Override
	public void deleteUser(final int sessionId) throws SessionExpired {
		final String stringUser = sessionManager.getUser(sessionId);
		try {
			userDAO.deleteUser(stringUser);
		} catch (final UnknownUserException e) {
			throw new RuntimeException(e);
		}
		sessionManager.closeSession(sessionId);

	}
	
	@Override
	public void deleteUser(final int sessionId, final String userId) 
			throws SessionExpired, InvalidRequest, UnknownUserException {
		if(!validateRequest(sessionId, RequestType.EDITUSER, null))
			throw new InvalidRequest(RequestType.EDITUSER, null);
		userDAO.deleteUser(userId);
		sessionManager.closeSession(sessionId);
	}

	/*
	 * UserInternalService interface methods
	 */

	@Override
	public boolean validateRequest(final int sessionId, final RequestType request, final FilePath filePath)
			throws SessionExpired {
		User user;
		try {
			user = userDAO.getUser(sessionManager.getUser(sessionId));
		} catch (final UnknownUserException e) {
			throw new RuntimeException(e);
		}
		switch (request) {
		case CREATEFILE:
			if (user.getUserLevel() == UserLevel.ADMIN || user.getUserLevel() == UserLevel.PROJECTLEADER) {
				return true;
			} else {
				return false;
			}
		case EDITPROJECT:
			if (user.getUserLevel() == UserLevel.ADMIN) {
				return true;
			} else if (user.isACollaborator(filePath)) {
				return true;
			} else {
				return false;
			}
		case DELETEFILE:
			if (user.getUserLevel() == UserLevel.ADMIN) {
				return true;
			} else if (user.getUserLevel() == UserLevel.ADMIN && user.isACollaborator(filePath)) {
				return true;
			} else {
				return false;
			}
		case EDITUSER:
			if (user.getUserLevel() == UserLevel.ADMIN ) {
				return true;
			} else {
				return false;
			}
		case MANAGEUSER:
			if (user.getUserLevel() == UserLevel.ADMIN || 
				(user.getUserLevel() == UserLevel.PROJECTLEADER && 
				user.isACollaborator(filePath)) ) {
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
	public void sudoAddUserToProject(final String userId, final FilePath project) 
			throws InexistentProject, UnknownUserException {
		if(!fileSystem.existsProject(project))
			throw new InexistentProject(project);
		final User user = userDAO.getUser(userId);
		user.addUserToProject(project);
		userDAO.storeUser(user);
	}

	@Override
	public void deleteReferences(final FilePath project){
		final List<String> userList = userDAO.searchUsers(null);
		for(final String userId: userList){
			User user;
			try {
				user = userDAO.getUser(userId);
			} catch (final UnknownUserException e) {
				throw new RuntimeException(e);
			}
			if(user.isACollaborator(project)){
				user.removeProject(project);
			}
			userDAO.storeUser(user);
		}
	}

	

}
