package server;

import java.io.File;
import java.util.List;

import files.datatypes.Project;
import files.datatypes.Version;
import sharedtypes.FilePath;
import users.datatypes.LoginInfo;
import users.datatypes.UserLevel;

/*
 * Adapter which offers all the external functionality of the different systems.
 */
public class SystemController implements ISystemController {

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
	public File getVersion(int sessionId, Version version) {
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

	@Override
	public int login(LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean changeLoginInfo(int sessionId, LoginInfo newInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<FilePath> getProjects(int sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createUser(int sessionId, LoginInfo newUserInfo,
			UserLevel newUserLevel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addUserToProject(int sessionId, String userId,
			FilePath project) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeLevel(int sessionId, String user, UserLevel newLevel) {
		// TODO Auto-generated method stub
		return false;
	}

}
