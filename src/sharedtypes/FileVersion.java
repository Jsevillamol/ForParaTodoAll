package sharedtypes;

import java.io.Serializable;
import java.util.Date;

/**
 * Identifies the specific version of a file,
 * with associated metadata.
 */
public class FileVersion implements Serializable{
	
	private static final long serialVersionUID = -4792585062651093500L;

	/**
	 * File this class is a version of.
	 */
	FilePath file;
	
	/**
	 * Identifier of this version.
	 */
	String versionId;
	
	/**
	 * Blame.
	 */
	String authorId;
	
	/**
	 * Date in which the version was created.
	 */
	Date date;
	
	/**
	 * Comment associated to version.
	 */
	String comment;
}
