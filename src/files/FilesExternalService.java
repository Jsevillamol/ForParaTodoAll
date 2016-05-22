package files;

import java.io.File;
import java.util.List;

import users.exceptions.UserException.SessionExpired;
import files.FileExceptions.InexistentFile;
import files.FileExceptions.InexistentProject;
import files.FileExceptions.ProjectAlreadyExists;
import files.FileExceptions.VersionAlreadyExists;
import files.datatypes.FilePath;
import files.datatypes.Project;
import files.datatypes.Version;

/**
 * Services offered by the Files system to external systems.
 */
public interface FilesExternalService {
	//Creation methods:
	/**
	 * Creates a project with the identifier indicated in FilePath.
	 * Associates a description to it, as well as a creator and a date of creation
	 * automatically.
	 * @throws ProjectAlreadyExists 
	 * @throws SessionExpired 
	 */
	void createProject(int sessionId, FilePath project, String description) throws SessionExpired, ProjectAlreadyExists;
	
	/**
	 * Updates the file version.
	 * If there was no previous version, creates a new file.
	 * @throws InexistentProject 
	 * @throws VersionAlreadyExists 
	 */
	void updateFile(int sessionId, FilePath path, File file, String comment) throws VersionAlreadyExists, InexistentProject;
	
	//Access methods:
	/**
	 * Returns a list of the hierarchy of files within a project.
	 */
	Project getProject(int sessionId, FilePath project);
	
	/**
	 * Returns a list of versions associated to a file,
	 * together with some extra information.
	 */
	List<Version> getHistory(int sessionId, FilePath file);
	
	/**
	 * Returns a list of projects matching the regex.
	 */
	List<FilePath> findProjects(int sessionId, String regex);
	
	/**
	 * Returns a specific version of a file.
	 */
	File getVersion(int sessionId, Version version, FilePath path);
	
	//Deletion methods:
	/**
	 * Deletes the file represented by path.
	 * @throws InexistentFile 
	 * @throws InexistentProject 
	 */
	void deleteFile(int sessionId, FilePath path) throws InexistentProject, InexistentFile;
	
	/**
	 * Deletes the project represented by the FilePath.
	 * @throws InexistentFile 
	 * @throws InexistentProject 
	 */
	void deleteProject(int sessionId, FilePath project) throws InexistentProject, InexistentFile;
}
