package files;

import java.io.File;
import java.util.List;

import users.UserInternalService;
import users.UserMain;
import users.datatypes.RequestType;
import users.exceptions.UserException.SessionExpired;
import files.FileExceptions.ProjectAlreadyExists;
import files.datatypes.FilePath;
import files.datatypes.Project;
import files.datatypes.Version;
import files.subsystems.FileDAO;
import files.subsystems.IFileDAO;

/**
 * Singleton main class for the file subsystem.
 * Includes a static factory method to get a reference to the system.
 * 
 * Depends on the Users module.
 * Handles all calls to the File subsystem and interactions with other modules.
 */
public class FileMain implements FilesExternalService{
	
	/**
	 * Data Access Object which allows interactions with the 
	 * project repository.
	 */
	IFileDAO fileDAO = FileDAO.getReference();
	
	/**
	 * Interface to user subsystem for request validation, etc.
	 */
	UserInternalService userSystem = UserMain.getUserInternalService();
	
	/**
	 * Creation of instances aside from singleton disallowed.
	 */
	private FileMain(){};
	
	private static FileMain singleton = null;
	
	/**
	 * Singleton factory.
	 * Returns a reference to the singleton.
	 * If there is no reference yet, creates it.
	 */
	private synchronized static FileMain getReference(){
		if(singleton == null)
			singleton = new FileMain();
		return singleton;
	}
	
	/**
	 * Method for the singleton as a FileExternalService
	 */
	public static FilesExternalService getExternalService(){
		return getReference();
	}
	
	@Override
	public void createProject(final int sessionId, final FilePath project, final String description) throws SessionExpired, ProjectAlreadyExists {
		if(userSystem.validateRequest(sessionId, RequestType.CreateProjectRequest, project)){
			fileDAO.createProject(project, description);
		}
		
	}

	@Override
	public void updateFile(final int sessionId, final FilePath path, final File file, final String comment) {
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
	public File getVersion(final int sessionId, final Version version) {
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
	public List<FilePath> findProjects(final int sessionId, final String regex) {
		// TODO Auto-generated method stub
		return null;
	}

}
