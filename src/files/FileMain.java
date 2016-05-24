package files;

import java.io.File;
import java.util.List;

import users.UserException.SessionExpired;
import users.UserException.UnknownUserException;
import users.UserInternalService;
import users.UserMain;
import users.datatypes.RequestType;
import files.FileExceptions.InexistentFile;
import files.FileExceptions.InexistentProject;
import files.FileExceptions.InexistentVersion;
import files.FileExceptions.InvalidRequest;
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
public class FileMain implements FilesExternalService, FilesInternaService{
	
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
	
	/**
	 * Abstract factory for Internal Services singleton.
	 * @return
	 */
	public static FilesInternaService getInternalService(){
		return getReference();
	}
	
	@Override
	public void createProject(
			final int sessionId, 
			final FilePath project, 
			final String description) 
					throws SessionExpired, ProjectAlreadyExists, InvalidRequest, UnknownUserException {
		if(userSystem.validateRequest(sessionId, RequestType.Create, project)){
			fileDAO.createProject(project, description);
		}
		else throw new InvalidRequest(RequestType.Create, project);
		
	}

	@Override
	public void updateFile(
			final int sessionId, final FilePath path, 
			final File file, final String comment) 
					throws VersionAlreadyExists, InexistentProject, InvalidRequest, UnknownUserException, SessionExpired {
		if(userSystem.validateRequest(sessionId, RequestType.Edit, path)){
			final Version version = new Version(comment, userSystem.identify(sessionId), null);
			fileDAO.storeFile(file, version, path);
		}
		else throw new InvalidRequest(RequestType.Edit, path);
	}

	@Override
	public Project getProject(final int sessionId, final FilePath project) 
			throws InexistentProject, InvalidRequest, UnknownUserException, SessionExpired {
		if(userSystem.validateRequest(sessionId, RequestType.Consult, project)){
			return fileDAO.getProject(project);
		}
		else throw new InvalidRequest(RequestType.Consult, project);
	}

	@Override
	public List<Version> getHistory(final int sessionId, final FilePath file) 
			throws InvalidRequest, InexistentProject, InexistentFile, UnknownUserException, SessionExpired {
		if(userSystem.validateRequest(sessionId, RequestType.Consult, file)){
			return fileDAO.getVersions(file);
		}
		else throw new InvalidRequest(RequestType.Consult, file);
	}

	@Override
	public File getVersion(final int sessionId, final Version version, final FilePath path) 
			throws InexistentProject, InexistentFile, InexistentVersion, InvalidRequest, UnknownUserException, SessionExpired {
		if(userSystem.validateRequest(sessionId, RequestType.Consult, path)){
			return fileDAO.getFile(path, version.getId());
		}
		else throw new InvalidRequest(RequestType.Consult, path);
	}

	@Override
	public void deleteFile(final int sessionId, final FilePath path) 
			throws InexistentProject, InexistentFile, InvalidRequest, UnknownUserException, SessionExpired {
		if(userSystem.validateRequest(sessionId, RequestType.Delete, path)){
			fileDAO.deleteFile(path);;
		}
		else throw new InvalidRequest(RequestType.Delete, path);
		
	}

	@Override
	public void deleteProject(final int sessionId, final FilePath project) 
			throws InexistentProject, InvalidRequest, UnknownUserException, SessionExpired {
		if(userSystem.validateRequest(sessionId, RequestType.Delete, project)){
			fileDAO.deleteProject(project);;
		}
		else throw new InvalidRequest(RequestType.Delete, project);
		
	}

	@Override
	public List<FilePath> findProjects(final int sessionId, final String regex) 
			throws InvalidRequest, UnknownUserException, SessionExpired {
		if(userSystem.validateRequest(sessionId, RequestType.Consult, null)){
			return (List<FilePath>) fileDAO.findProjects(regex);
		}
		else throw new InvalidRequest(RequestType.Consult, null);
	}
	
	/*
	 * FilesInternalService methods.
	 */
	
	@Override
	public boolean existsProject(final FilePath project) {
		// TODO Auto-generated method stub
		return fileDAO.existsProject(project);
	}

}
