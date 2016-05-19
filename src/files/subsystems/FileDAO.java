package files.subsystems;

import java.io.File;
import java.util.List;

import sharedtypes.FilePath;
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
	public Project getProject(FilePath project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project createProject(Project project, String description) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProject(FilePath project) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<FilePath> findProjects(String regex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Version> getVersions(FilePath file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getFile(Version version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeFile(File file, Version version, FilePath path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteFile(FilePath file) {
		// TODO Auto-generated method stub

	}

}
