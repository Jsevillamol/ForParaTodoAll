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
	
	public Version(String comment, String author, Date date){
		this.id = author+date;
		this.author = author;
		this.date = date;
		this.comment = comment;
	}
	
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


}
