package client.connect;

import java.io.File;
import java.util.List;

import server.Endpoint;
import server.ISystemController;
import users.datatypes.LoginInfo;
import users.datatypes.UserLevel;
import users.exceptions.UserException.InvalidRequest;
import users.exceptions.UserException.SessionExpired;
import users.exceptions.UserException.UnknownUserException;
import files.datatypes.FilePath;
import files.datatypes.Project;
import files.datatypes.Version;

/**
 * Wraps calls to a controller in a SystemResponse and passes them
 * to an Endpoint to be sent to the server.
 */
public class ProxyController implements ISystemController {
	
	/**
	 * Class to which generated messages are sent to be communicated.
	 */
	Endpoint sender;
	
	public ProxyController(final Endpoint sender){
		this.sender = sender;
	}
	
	@Override
	public int login(final LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void changeLoginInfo(final int sessionId, final LoginInfo newInfo) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<FilePath> getProjects(final int sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createUser(final int sessionId, final LoginInfo newUserInfo,
			final UserLevel newUserLevel) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addUserToProject(final int sessionId, final String userId,
			final FilePath project) {
		// TODO Auto-generated method stub
	}

	@Override
	public void changeLevel(final int sessionId, final String user, final UserLevel newLevel) {
		// TODO Auto-generated method stub
	}

	@Override
	public void createProject(final int sessionId, final FilePath project, final String description) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateFile(final int sessionId, final FilePath path, final File file,
			final String comment) {
		// TODO Auto-generated method stub

	}

	@Override
	public Project getProject(final int sessionId, final FilePath project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Version> getHistory(final int sessionId, final FilePath file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FilePath> findProjects(final int sessionId, final String regex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getVersion(final int sessionId, final Version version, final FilePath path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFile(final int sessionId, final FilePath path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProject(final int sessionId, final FilePath project) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(final int sessionId) throws SessionExpired {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserFromProject(final int sessionId, final String userId, final FilePath project)
			throws SessionExpired, UnknownUserException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logoff(final int sessionId) throws SessionExpired {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(final int sessionId, final String userId) throws SessionExpired, InvalidRequest, UnknownUserException {
		// TODO Auto-generated method stub
		
	}

}
