package files.datatypes;

import java.io.Serializable;

/**
 * Token which symbolizes a project, folder of file in the system.
 * If path is null, then it represents a project.
 * If not, this class represents the path to a file relative to the project.
 */
public class FilePath implements Serializable {
	
	private static final long serialVersionUID = 2360837520640879115L;

	/**
	 * Identifier of a project
	 */
	String projectId;
	
	/**
	 * Relative path of a file within a project.
	 * If null, then the FilePath represents just the project with id projectiId.
	 */
	String path;
}
