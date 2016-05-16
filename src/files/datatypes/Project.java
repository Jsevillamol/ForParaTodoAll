package files.datatypes;

import java.util.List;

import sharedtypes.FilePath;

/*
 * 
 */
public class Project {
	String id;
	String description;
	
	/*
	 * List of files within the project.
	 * If a folder does not contain any file, it is also included as a filepath.
	 */
	List<FilePath> files;
	
}
