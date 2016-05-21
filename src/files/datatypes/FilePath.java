package files.datatypes;

import java.io.Serializable;

/**
 * Token which symbolizes a project, folder of file in the system.
 * If path is null, then it represents a project.
 * If not, this class represents the path to a file relative to the project.
 */
public class FilePath implements Serializable {
	
	public FilePath(final String projectId, final String path) {
		this.projectId = projectId;
		this.path = path;
	}

	public FilePath(final String projectId2) {
		this.projectId = projectId2;
		this.path = null;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(final String projectId) {
		this.projectId = projectId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(final String path) {
		this.path = path;
	}

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
