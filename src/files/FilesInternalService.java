package files;

import files.datatypes.FilePath;

/**
 * Functionalities offered by the files subsystem to
 * other subsystems.
 * @author Jaime
 *
 */
public interface FilesInternalService {
	/**
	 * Returns true iff the project exists.
	 * Intended to be used by Users to prevent adding collaborators
	 * to project which do not exist.
	 * @param project
	 * @return
	 */
	boolean existsProject(FilePath project);
}
