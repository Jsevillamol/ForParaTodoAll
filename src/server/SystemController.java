package server;

import java.io.File;
import java.util.List;

import files.FileMain;
import files.FilesExternalService;
import files.datatypes.FilePath;
import files.datatypes.Project;
import files.datatypes.Version;
import users.UserExternalService;
import users.UserMain;
import users.datatypes.LoginInfo;
import users.datatypes.UserLevel;
import users.exceptions.UserException.UnknownUserException;

/**
 * Adapter which offers all the external functionality of the different systems.
 */
public class SystemController implements ISystemController {
	
	/**
	 * Handles petitions related to users.
	 */
	private UserExternalService usersSystem = UserMain.getUserExternalService();
	
	/**
	 * Handles petitions related to files.
	 */
	private FilesExternalService filesSystem = FileMain.getExternalService();
	
	@Override
	public void createProject(int sessionId, FilePath project, String description) {
		filesSystem.createProject(sessionId, project, description);
	}

	@Override
	public void updateFile(int sessionId, FilePath path, File file,
			String comment) {
		filesSystem.updateFile(sessionId, path, file, comment);
		
	}

	@Override
	public Project getProject(int sessionId, FilePath project) {
		return filesSystem.getProject(sessionId, project);
	}

	@Override
	public List<Version> getHistory(int sessionId, FilePath file) {
		return filesSystem.getHistory(sessionId, file);
	}

	@Override
	public List<FilePath> findProjects(int sessionId, String regex) {
		return filesSystem.findProjects(sessionId, regex);
	}

	@Override
	public File getVersion(int sessionId, Version version) {
		return filesSystem.getVersion(sessionId, version);
	}

	@Override
	public void deleteFile(int sessionId, FilePath path) {
		filesSystem.deleteFile(sessionId, path);
		
	}

	@Override
	public void deleteProject(int sessionId, FilePath project) {
		filesSystem.deleteFile(sessionId, project);
		
	}

	@Override
	public int login(LoginInfo loginInfo) throws UnknownUserException {
		return usersSystem.login(loginInfo);
	}

	@Override
	public boolean changeLoginInfo(int sessionId, LoginInfo newInfo) {
		return usersSystem.changeLoginInfo(sessionId, newInfo);
	}

	@Override
	public List<FilePath> getProjects(int sessionId) {
		return usersSystem.getProjects(sessionId);
	}

	@Override
	public boolean createUser(int sessionId, LoginInfo newUserInfo,
			UserLevel newUserLevel) {
		return usersSystem.createUser(sessionId, newUserInfo, newUserLevel);
	}

	@Override
	public boolean addUserToProject(int sessionId, String userId,
			FilePath project) {
		return usersSystem.addUserToProject(sessionId, userId, project);
	}

	@Override
	public boolean changeLevel(int sessionId, String user, UserLevel newLevel) {
		return usersSystem.changeLevel(sessionId, user, newLevel);
	}

}
