package files.subsystems;

import java.io.File;
import java.util.List;

import sharedtypes.FilePath;
import files.datatypes.Project;
import files.datatypes.Version;

/*
 * Controls access to the actual repository of files.
 */
public interface IFileDAO {
	/*
	 * Returns the project file hierarchy of the project represented by FilePath,
	 * plus metadata.
	 */
	Project getProject(FilePath project);
	
	/*
	 * Stores a new project.
	 * If a project with the same id already existed,
	 * throws an error.
	 */
	Project createProject(Project project, String description);
	
	/*
	 * Delete the folder of a project, together with its contents.
	 */
	void deleteProject(FilePath project);
	
	/*
	 * Returns a list of projects whose id matches the regex.
	 */
	List<FilePath> findProjects(String regex);
	
	/*
	 * Returns a list of the versions of a file, with metadata.
	 */
	List<Version> getVersions(FilePath file);
	
	/*
	 * Returns the file represented by version.
	 */
	File getFile(Version version);
	
	/*
	 * Stores the file together with its version info.
	 * Throws an error if there was already a version of the file with same id.
	 */
	void storeFile(File file, Version version, FilePath path);
	
	/*
	 * Deletes all versions of the file represented by the filePath.
	 */
	void deleteFile(FilePath file);
}
