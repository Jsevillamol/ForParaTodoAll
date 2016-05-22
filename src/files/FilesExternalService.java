package files;

import java.io.File;
import java.util.List;

import users.exceptions.UserException.SessionExpired;
import files.FileExceptions.InexistentFile;
import files.FileExceptions.InexistentProject;
import files.FileExceptions.InexistentVersion;
import files.FileExceptions.InvalidRequest;
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
	 * @throws InvalidRequest 
	 */
	void createProject(int sessionId, FilePath project, String description) throws SessionExpired, ProjectAlreadyExists, InvalidRequest;
	
	/**
	 * Updates the file version.
	 * If there was no previous version, creates a new file.
	 * @throws InexistentProject 
	 * @throws VersionAlreadyExists 
	 * @throws InvalidRequest 
	 */
	void updateFile(int sessionId, FilePath path, File file, String comment) throws VersionAlreadyExists, InexistentProject, InvalidRequest;
	
	//Access methods:
	/**
	 * Returns a list of the hierarchy of files within a project.
	 * @throws InexistentProject 
	 * @throws InvalidRequest 
	 */
	Project getProject(int sessionId, FilePath project) throws InexistentProject, InvalidRequest;
	
	/**
	 * Returns a list of versions associated to a file,
	 * together with some extra information.
	 * @throws InvalidRequest 
	 * @throws InexistentFile 
	 * @throws InexistentProject 
	 */
	List<Version> getHistory(int sessionId, FilePath file) throws InvalidRequest, InexistentProject, InexistentFile;
	
	/**
	 * Returns a list of projects matching the regex.
	 * @throws InvalidRequest 
	 */
	List<FilePath> findProjects(int sessionId, String regex) throws InvalidRequest;
	
	/**
	 * Returns a specific version of a file.
	 * @throws InexistentVersion 
	 * @throws InexistentFile 
	 * @throws InexistentProject 
	 * @throws InvalidRequest 
	 */
	File getVersion(int sessionId, Version version, FilePath path) throws InexistentProject, InexistentFile, InexistentVersion, InvalidRequest;
	
	//Deletion methods:
	/**
	 * Deletes the file represented by path.
	 * @throws InexistentFile 
	 * @throws InexistentProject 
	 * @throws InvalidRequest 
	 */
	void deleteFile(int sessionId, FilePath path) throws InexistentProject, InexistentFile, InvalidRequest;
	
	/**
	 * Deletes the project represented by the FilePath.
	 * @throws InexistentFile 
	 * @throws InexistentProject 
	 * @throws InvalidRequest 
	 */
	void deleteProject(int sessionId, FilePath project) throws InexistentProject, InexistentFile, InvalidRequest;
}
