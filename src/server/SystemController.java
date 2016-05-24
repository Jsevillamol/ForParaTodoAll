package server;

import java.io.File;
import java.util.List;

import users.UserExternalService;
import users.UserMain;
import users.datatypes.LoginInfo;
import users.datatypes.UserLevel;
import users.exceptions.UserException.IncorrectPassword;
import users.exceptions.UserException.InvalidRequest;
import users.exceptions.UserException.SessionExpired;
import users.exceptions.UserException.UnknownUserException;
import users.exceptions.UserException.UserAlreadyExists;
import files.FileMain;
import files.FilesExternalService;
import files.datatypes.FilePath;
import files.datatypes.Project;
import files.datatypes.Version;
import files.exceptions.FileException.InexistentFile;
import files.exceptions.FileException.InexistentProject;
import files.exceptions.FileException.InexistentVersion;
import files.exceptions.FileException.ProjectAlreadyExists;
import files.exceptions.FileException.VersionAlreadyExists;

/**
 * Controller which offers all the external functionality of the different
 * systems. Also acts as a bridge, decoupling the implementation of the
 * subsystems.
 */
public class SystemController implements ISystemController {

	/**
	 * Handles petitions related to users.
	 */
	private final UserExternalService usersSystem = UserMain.getUserExternalService();

	/**
	 * Handles petitions related to files.
	 */
	private final FilesExternalService filesSystem = FileMain.getExternalService();

	@Override
	public void createProject(final int sessionId, final FilePath project, final String description)
			throws SessionExpired, ProjectAlreadyExists, InvalidRequest {
		filesSystem.createProject(sessionId, project, description);
	}

	@Override
	public void updateFile(final int sessionId, final FilePath path, final File file, final String comment)
			throws VersionAlreadyExists, InexistentProject, InvalidRequest, SessionExpired {
		filesSystem.updateFile(sessionId, path, file, comment);
	}

	@Override
	public Project getProject(final int sessionId, final FilePath project)
			throws InexistentProject, InvalidRequest, SessionExpired {
		return filesSystem.getProject(sessionId, project);
	}

	@Override
	public List<Version> getHistory(final int sessionId, final FilePath file)
			throws InvalidRequest, InexistentProject, InexistentFile, SessionExpired {
		return filesSystem.getHistory(sessionId, file);
	}

	@Override
	public List<FilePath> findProjects(final int sessionId, final String regex)
			throws InvalidRequest, UnknownUserException, SessionExpired {
		return filesSystem.findProjects(sessionId, regex);
	}

	@Override
	public File getVersion(final int sessionId, final Version version, final FilePath path) throws InexistentProject,
			InexistentFile, InexistentVersion, InvalidRequest, SessionExpired {
		return filesSystem.getVersion(sessionId, version, path);
	}

	@Override
	public void deleteFile(final int sessionId, final FilePath path)
			throws InexistentProject, InexistentFile, InvalidRequest, SessionExpired {
		filesSystem.deleteFile(sessionId, path);

	}

	@Override
	public void deleteProject(final int sessionId, final FilePath project)
			throws InexistentProject, InexistentFile, InvalidRequest, SessionExpired {
		filesSystem.deleteFile(sessionId, project);

	}

	@Override
	public int login(final LoginInfo loginInfo) throws UnknownUserException, IncorrectPassword {
		return usersSystem.login(loginInfo);
	}

	@Override
	public void changeLoginInfo(final int sessionId, final LoginInfo newInfo)
			throws SessionExpired {
		usersSystem.changeLoginInfo(sessionId, newInfo);
	}

	@Override
	public List<FilePath> getProjects(final int sessionId) throws SessionExpired {
		return usersSystem.getProjects(sessionId);
	}

	@Override
	public void createUser(final int sessionId, final LoginInfo newUserInfo, final UserLevel newUserLevel) 
			throws SessionExpired, InvalidRequest, UserAlreadyExists {
		usersSystem.createUser(sessionId, newUserInfo, newUserLevel);
	}

	@Override
	public void addUserToProject(final int sessionId, final String userId, final FilePath project)
			throws UnknownUserException, SessionExpired, InexistentProject, InvalidRequest {
		usersSystem.addUserToProject(sessionId, userId, project);
	}

	@Override
	public void changeLevel(final int sessionId, final String user, final UserLevel newLevel)
			throws UnknownUserException, SessionExpired, InvalidRequest {

		usersSystem.changeLevel(sessionId, user, newLevel);
	}

	@Override
	public void deleteUser(final int sessionId) 
			throws SessionExpired {
		usersSystem.deleteUser(sessionId);
	}

	@Override
	public void deleteUserFromProject(final int sessionId, final String userId, final FilePath project)
			throws SessionExpired, UnknownUserException, InexistentProject, InvalidRequest {
		usersSystem.deleteUserFromProject(sessionId, userId, project);

	}

	@Override
	public void logoff(final int sessionId) throws SessionExpired {
		usersSystem.logoff(sessionId);	
	}

	@Override
	public void deleteUser(final int sessionId, final String userId) throws SessionExpired,
			InvalidRequest, UnknownUserException {
		usersSystem.deleteUser(sessionId, userId);
	}

}
