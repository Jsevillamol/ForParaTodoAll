package files;

/**
 * Exception generated by the file subsystem.
 * @author Jaime
 *
 */
public class FileExceptions extends Exception{
	private static final long serialVersionUID = 2219233707558869134L;
	
	/**
	 * Exception thrown when a project id which is not supposed to be used
	 * is already in the database.
	 * @author Jaime
	 *
	 */
	public static class ProjectAlreadyExists extends FileExceptions{

		private static final long serialVersionUID = -3768878269292978253L;
		
	}
}
