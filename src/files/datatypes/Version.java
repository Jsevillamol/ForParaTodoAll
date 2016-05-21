package files.datatypes;

import java.io.Serializable;
import java.util.Date;

/**
 * Identifies a specific version of a file in a project.
 */
public class Version implements Serializable {
	private static final long serialVersionUID = -7211035247610743986L;
	
	/**
	 * Unique identifier of the version.
	 * They are automatically generated.
	 */
	String id;
	
	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * A user comment of the version.
	 */
	String comment;
	
	/**
	 * Who uploaded the version.
	 * It is found from the sessionId.
	 */
	String author;
	
	/**
	 * The date when the version of the file was upload.
	 */
	Date date;
}
