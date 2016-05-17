package files.datatypes;

import java.io.Serializable;
import java.util.List;

import sharedtypes.FilePath;

/*
 * 
 */
public class Project implements Serializable {
	
	private static final long serialVersionUID = 4824134341813495012L;
	
	String id;
	String description;
	
	/*
	 * List of files within the project.
	 * If a folder does not contain any file, it is also included as a filepath.
	 */
	List<FilePath> files;
	
}
