package files.datatypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A representation of a project.
 * It contains an ID, a description and a list of files.
 */
public class Project implements Serializable {
	
	/*
	 * Creates a new project with values already initialized.
	 */
	public Project(final String id, final String description, final Collection<FilePath> files) {
		this.id = id;
		this.description = description;
		this.files = new ArrayList<FilePath>(files);
	}

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
