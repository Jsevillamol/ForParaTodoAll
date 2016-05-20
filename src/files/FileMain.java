package files;

import java.io.File;
import java.util.List;

import files.datatypes.FilePath;
import files.datatypes.Project;
import files.datatypes.Version;
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
	IFileDAO fileDAO;
	
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
	private static FileMain getReference(){
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
	public void createProject(int sessionId, FilePath project, String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFile(int sessionId, FilePath path, File file, String comment) {
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
	public List<FilePath> findProjects(int sessionId, String regex) {
		// TODO Auto-generated method stub
		return null;
	}

}
