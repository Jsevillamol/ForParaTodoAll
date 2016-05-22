package files;

import java.io.File;
import java.util.List;

import users.UserInternalService;
import users.UserMain;
import users.datatypes.RequestType;
import users.exceptions.UserException.SessionExpired;
import files.FileExceptions.InexistentFile;
import files.FileExceptions.InexistentProject;
import files.FileExceptions.ProjectAlreadyExists;
import files.FileExceptions.VersionAlreadyExists;
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
		if(userSystem.validateRequest(sessionId, RequestType.Create, project)){
			fileDAO.createProject(project, description);
		}
		
	}

	@Override
	public void updateFile(final int sessionId, final FilePath path, final File file, final String comment) throws VersionAlreadyExists, InexistentProject {
		if(userSystem.validateRequest(sessionId, RequestType.Edit, path)){
			Version version = new Version(comment, userSystem.identify(sessionId), null);
			fileDAO.storeFile(file, version, path);
		}
		
	}

	@Override
	public Project getProject(final int sessionId, final FilePath project) {
		if(userSystem.validateRequest(sessionId, RequestType.Edit, project)){
			return fileDAO.getProject(project);
		}
		else return null;
	}

	@Override
	public List<Version> getHistory(final int sessionId, final FilePath file) {
		if(userSystem.validateRequest(sessionId, RequestType.Edit, file)){
			return fileDAO.getVersions(file);
		}
		return null;
	}

	@Override
	public File getVersion(final int sessionId, final Version version, FilePath path) {
		if(userSystem.validateRequest(sessionId, RequestType.Edit, path)){
			return fileDAO.getFile(path, version.getId());
		}
		return null;
	}

	@Override
	public void deleteFile(final int sessionId, final FilePath path) throws InexistentProject, InexistentFile {
		if(userSystem.validateRequest(sessionId, RequestType.Edit, path)){
			fileDAO.deleteFile(path);;
		}
		
	}

	@Override
	public void deleteProject(final int sessionId, final FilePath project) {
		if(userSystem.validateRequest(sessionId, RequestType.Delete, project)){
			fileDAO.deleteProject(project);;
		}
		
	}

	@Override
	public List<FilePath> findProjects(final int sessionId, final String regex) {
		////////
		return (List<FilePath>) fileDAO.findProjects(regex);
	}

}
