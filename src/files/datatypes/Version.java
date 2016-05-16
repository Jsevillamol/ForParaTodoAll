package files.datatypes;

import java.util.Date;

/*
 * Identifies a specific version of a file in a project.
 */
public class Version {
	/*
	 * Unique identifier of the version.
	 * They are automatically generated.
	 */
	String id;
	String comment;
	/*
	 * Who uploaded the version.
	 * It is found from the sessionId.
	 */
	String author;
	Date date;
}
