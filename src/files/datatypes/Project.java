package files.datatypes;

import java.io.Serializable;
import java.util.List;

/**
 * A representation of a project.
 * It contains an ID, a description and a list of files.
 */
public class Project implements Serializable {
	
	private static final long serialVersionUID = 4824134341813495012L;
	/**
	 * Determines univocally a proyect.
	 * */
	String id;
	
	/**
	 * The name of the proyect.
	 * */
	String description;
	
	/**
	 * List of files within the project.
	 * If a folder does not contain any file, it is also included as a filepath.
	 */
	List<FilePath> files;
	
}
