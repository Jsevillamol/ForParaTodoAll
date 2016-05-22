package client.connect;

import java.io.File;
import java.util.List;

import files.datatypes.FilePath;
import files.datatypes.Project;
import files.datatypes.Version;
import server.Endpoint;
import server.ISystemController;
import users.datatypes.LoginInfo;
import users.datatypes.UserLevel;

/**
 * Wraps calls to a controller in a SystemResponse and passes them
 * to an Endpoint to be sent to the server.
 */
public class ProxyController implements ISystemController {
	
	/**
	 * Class to which generated messages are sent to be communicated.
	 */
	Endpoint sender;
	
	public ProxyController(Endpoint sender){
		this.sender = sender;
	}
	
	@Override
	public int login(LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void changeLoginInfo(int sessionId, LoginInfo newInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<FilePath> getProjects(int sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createUser(int sessionId, LoginInfo newUserInfo,
			UserLevel newUserLevel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addUserToProject(int sessionId, String userId,
			FilePath project) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void changeLevel(int sessionId, String user, UserLevel newLevel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createProject(int sessionId, FilePath project, String description) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateFile(int sessionId, FilePath path, File file,
			String comment) {
		// TODO Auto-generated method stub

	}

	@Override
	public Project getProject(int sessionId, FilePath project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Version> getHistory(int sessionId, FilePath file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FilePath> findProjects(int sessionId, String regex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getVersion(int sessionId, Version version, FilePath path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFile(int sessionId, FilePath path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProject(int sessionId, FilePath project) {
		// TODO Auto-generated method stub

	}

}
