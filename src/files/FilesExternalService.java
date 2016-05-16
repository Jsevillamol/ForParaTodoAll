package files;

import java.io.File;
import java.util.List;

import files.datatypes.Project;
import files.datatypes.Version;
import sharedtypes.FilePath;

public interface FilesExternalService {
	//Creation methods
	void createProject(int sessionId, FilePath project);
	
	/*
	 * Updates the file version.
	 * If there was no previous version, creates a new file.
	 */
	void updateFile(int sessionId, FilePath path, File file, String comment);
	
	//Access methods
	/*
	 * Returns a list of the hierarchy of files within a project.
	 */
	Project getProject(int sessionId, FilePath project);
	
	/*
	 * Returns a list of versions associated to a file,
	 * together with some extra information.
	 */
	List<Version> getHistory(int sessionId, FilePath file);
	
	/*
	 * Returns a list of projects matching the regex.
	 */
	List<FilePath> findProjects(int sessionId, String regex);
	
	/*
	 * Returns a specific version of a file.
	 */
	File getVersion(int sessionId, Version version);
	
	//Deletion methods
	void deleteFile(int sessionId, FilePath path);
	void deleteProject(int sessionId, FilePath project);
}
