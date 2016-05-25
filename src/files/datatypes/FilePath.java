package files.datatypes;

import java.io.Serializable;

/**
 * Token which symbolizes a project, folder of file in the system.
 * If path is null, then it represents a project.
 * If not, this class represents the path to a file relative to the project.
 */
public class FilePath implements Serializable, Comparable<FilePath> {
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result
				+ ((projectId == null) ? 0 : projectId.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final FilePath other = (FilePath) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		return true;
	}

	@Override
	public int compareTo(final FilePath o) {
		if(this.path == null)
			return this.projectId.compareTo(o.projectId);
		else{
			final int res = this.projectId.compareTo(o.projectId);
			return (res != 0)
					? res
					: this.path.compareTo(o.getPath());
		}
	}
}
