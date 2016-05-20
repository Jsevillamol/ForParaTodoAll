package files.subsystems;

import java.io.File;
import java.util.List;

import files.datatypes.FilePath;
import files.datatypes.Project;
import files.datatypes.Version;

/**
 * Singleton implementing IFileDAO.
 */
public class FileDAO implements IFileDAO {
	
	/**
	 * Creation of instances aside from singleton disallowed.
	 */
	private FileDAO(){};
	
	private static FileDAO singleton = null;
	
	/**
	 * Singleton method
	 */
	public static IFileDAO getReference(){
		if(singleton == null)
			singleton = new FileDAO();
		return singleton;
	}
	
	@Override
	public Project getProject(final FilePath project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project createProject(final FilePath project, final String description) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProject(final FilePath project) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<FilePath> findProjects(final String regex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Version> getVersions(final FilePath file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getFile(final Version version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeFile(final File file, final Version version, final FilePath path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteFile(final FilePath file) {
		// TODO Auto-generated method stub

	}

}
