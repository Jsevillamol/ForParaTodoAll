package server;

import java.io.File;
import java.util.List;

import users.UserExternalService;
import users.UserMain;
import users.datatypes.LoginInfo;
import users.datatypes.UserLevel;
import users.exceptions.UserException.SessionExpired;
import users.exceptions.UserException.UnknownUserException;
import files.FileExceptions.ProjectAlreadyExists;
import files.FileMain;
import files.FilesExternalService;
import files.datatypes.FilePath;
import files.datatypes.Project;
import files.datatypes.Version;

/**
 * Adapter which offers all the external functionality of the different systems.
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
	public void createProject(final int sessionId, final FilePath project, final String description) throws SessionExpired, ProjectAlreadyExists {
		filesSystem.createProject(sessionId, project, description);
	}

	@Override
	public void updateFile(final int sessionId, final FilePath path, final File file,
			final String comment) {
		filesSystem.updateFile(sessionId, path, file, comment);
		
	}

	@Override
	public Project getProject(final int sessionId, final FilePath project) {
		return filesSystem.getProject(sessionId, project);
	}

	@Override
	public List<Version> getHistory(final int sessionId, final FilePath file) {
		return filesSystem.getHistory(sessionId, file);
	}

	@Override
	public List<FilePath> findProjects(final int sessionId, final String regex) {
		return filesSystem.findProjects(sessionId, regex);
	}

	@Override
	public File getVersion(final int sessionId, final Version version) {
		return filesSystem.getVersion(sessionId, version);
	}

	@Override
	public void deleteFile(final int sessionId, final FilePath path) {
		filesSystem.deleteFile(sessionId, path);
		
	}

	@Override
	public void deleteProject(final int sessionId, final FilePath project) {
		filesSystem.deleteFile(sessionId, project);
		
	}

	@Override
	public int login(final LoginInfo loginInfo) throws UnknownUserException {
		return usersSystem.login(loginInfo);
	}

	@Override
	public boolean changeLoginInfo(final int sessionId, final LoginInfo newInfo) {
		return usersSystem.changeLoginInfo(sessionId, newInfo);
	}

	@Override
	public List<FilePath> getProjects(final int sessionId) {
		return usersSystem.getProjects(sessionId);
	}

	@Override
	public boolean createUser(final int sessionId, final LoginInfo newUserInfo,
			final UserLevel newUserLevel) {
		return usersSystem.createUser(sessionId, newUserInfo, newUserLevel);
	}

	@Override
	public boolean addUserToProject(final int sessionId, final String userId,
			final FilePath project) {
		return usersSystem.addUserToProject(sessionId, userId, project);
	}

	@Override
	public boolean changeLevel(final int sessionId, final String user, final UserLevel newLevel) {
		return usersSystem.changeLevel(sessionId, user, newLevel);
	}

}
