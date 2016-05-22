package files.subsystems;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import files.FileExceptions.InexistentFile;
import files.FileExceptions.InexistentProject;
import files.FileExceptions.ProjectAlreadyExists;
import files.FileExceptions.VersionAlreadyExists;
import files.datatypes.FilePath;
import files.datatypes.Project;
import files.datatypes.Version;

/**
 * Singleton implementing IFileDAO.
 */
public class FileDAO implements IFileDAO {
	
	/*
	 * Note: the following classes are not intended for production,
	 * but for testing purposes.
	 * In a serious production environment, those classes would be replaced by an
	 * actual database.
	 */
	
	/**
	 * 
	 */
	private static class FileVersion{
		public FileVersion(final File file2, final Version version2) {
			this.file = file2;
			this.version = version2;
		}
		File file;
		Version version;
	}
	
	/**
	 * Tracked history of a file.
	 * Contains a snapshot of every version uploaded to the system.
	 * @author Jaime
	 *
	 */
	private static class FileHistory{
		public FileHistory(final FileVersion v) {
			fileHistory = new TreeMap<>();
			fileHistory.put(v.version.getId(), v);
		}

		public Map<String, FileVersion> fileHistory;
	}
	
	/**
	 * Each project has a repository associated.
	 * @author Jaime
	 *
	 */
	private static class Repository {
		public Repository(final String description) {
			repository = new TreeMap<>();
			this.description = description;
		}

		/*
		 * Keys are FilePath representing projects,
		 * values are the history of versions of each file.
		 */
		public Map<FilePath, FileHistory> repository;
		
		/**
		 * Description of the project.
		 */
		public String description;
	}
	
	/**
	 * The most outer layer of the database, which contains every project.
	 * Associates projectIds to repositories.
	 * @author Jaime
	 *
	 */
	private	Map<FilePath, Repository> database;
	
	/**
	 * Creation of instances aside from singleton disallowed.
	 */
	private FileDAO(){};
	
	private static FileDAO singleton = null;
	
	/**
	 * Singleton method
	 */
	public static synchronized IFileDAO getReference(){
		if(singleton == null)
			singleton = new FileDAO();
		return singleton;
	}
	
	@Override
	public Project getProject(final FilePath project) {
		final Repository repo = database.get(project);
		final Project res = new Project(project.getProjectId(), repo.description, repo.repository.keySet());
		return res;
	}

	@Override
	public void createProject(final FilePath project, final String description) throws ProjectAlreadyExists {
		if(database.containsKey(project))
			throw new ProjectAlreadyExists();
		final Repository repo = new Repository(description);
		database.put(project, repo);
	}

	@Override
	public void deleteProject(final FilePath project) {
		database.remove(project);
	}

	@Override
	public Collection<FilePath> findProjects(final String regex) {
		return database.keySet(); //TODO: Apply regex
	}

	@Override
	public List<Version> getVersions(final FilePath file) {
		final Repository repo = database.get(new FilePath(file.getProjectId()));
		final FileHistory fh = repo.repository.get(file);
		final List<Version> res = new ArrayList<>();
		for(final FileVersion v : fh.fileHistory.values()){
			res.add(v.version);
		}
		return res;
	}

	@Override
	public File getFile(final FilePath file, final String versionId) {
		final Repository repo = database.get(new FilePath(file.getProjectId()));
		final FileHistory fh = repo.repository.get(file);
		return fh.fileHistory.get(versionId).file;
	}

	@Override
	public void storeFile(final File file, final Version version, final FilePath path) throws VersionAlreadyExists, InexistentProject {
		final FilePath project = new FilePath(path.getProjectId());
		if(!database.containsKey(project))
			throw new InexistentProject(project);
		final Repository repo = database.get(project);
		if(repo.repository.containsKey(path)){
			final FileHistory fh = repo.repository.get(path);
			if(fh.fileHistory.containsKey(version.getId())){
				throw new VersionAlreadyExists();
			}
			fh.fileHistory.put(version.getId(), new FileVersion(file, version));
		} else { //File did not exists previously
			final FileVersion v = new FileVersion(file, version);
			repo.repository.put(path, new FileHistory(v));
		}
	}

	@Override
	public void deleteFile(final FilePath file) throws InexistentProject, InexistentFile {
		final FilePath project = new FilePath(file.getProjectId());
		if(!database.containsKey(project))
			throw new InexistentProject(project);
		final Repository repo = database.get(project);
		if(!repo.repository.containsKey(file))
			throw new InexistentFile(file);
		repo.repository.remove(file);
	}

}
