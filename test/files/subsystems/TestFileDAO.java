package files.subsystems;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import files.datatypes.FilePath;
import files.datatypes.Version;
import files.exceptions.FileException.InexistentProject;
import files.exceptions.FileException.ProjectAlreadyExists;
import files.exceptions.FileException.VersionAlreadyExists;

/**
 * Mock class to test FileDAO.
 * Unlike the original, which is a singleton, creates an independent database.
 * @author Jaime
 *
 */
@RunWith(Parameterized.class)
public class TestFileDAO extends FileDAO {
	
	public static IFileDAO getReference(){
		return new FileDAO();
	}
	
	private final FilePath PROJECT;
	private final FilePath PROJECT2;
	private final String PROJECT_DESCRIPTION;
	private final FilePath FILEPATH;
	private final FilePath FILEPATH2;
	private final File FILE;
	private final String USERID;
	private final String USERID2;
	private final Version VERSION;
	private final Version VERSION2;
	private final Date DATE;
	private final String REGEX;
	
	public TestFileDAO(final String pROJECT_NAME, final String pROJECT_NAME2,
			final String pROJECT_DESCRIPTION, final String fILE_NAME, final String fILE_NAME2,
			final String fILE, final String vERSION, final String vERSION2, final long ePOCH,
			final String vERSION_COMMENT, final String uSERID, final String uSERID2, final String rEGEX) {
		super();
		PROJECT = new FilePath(pROJECT_NAME);
		PROJECT2 = new FilePath(pROJECT_NAME2);
		PROJECT_DESCRIPTION = pROJECT_DESCRIPTION;
		FILEPATH = new FilePath(pROJECT_NAME, fILE_NAME);
		FILEPATH2 = new FilePath(pROJECT_NAME, fILE_NAME2);
		FILE = new File(fILE);
		DATE = new Date(ePOCH);
		USERID = uSERID;
		USERID2 = uSERID2;
		VERSION = new Version(vERSION_COMMENT, USERID, DATE);
		VERSION2 = new Version(vERSION_COMMENT, USERID2, DATE);
		REGEX = rEGEX;
	}
	
	@SuppressWarnings("rawtypes")
	@Parameterized.Parameters
	public static Collection testCases() {
	      return Arrays.asList(new Object[][] {
	         { "testProject", "testProject2", "A description", "fileName", "fileName2", "a file", 
	        	 "version", "version2", 1337, "A version comment", "user", "user2", "regex"}
	      });
	}

	/**
	 * Loads a project with a file in the database for testing.
	 */
	@Before
	public void setUp(){
		
		try {
			createProject(PROJECT, PROJECT_DESCRIPTION);
		} catch (final ProjectAlreadyExists e) {
			e.printStackTrace();
		}
		
		try{
			storeFile(FILE, VERSION, FILEPATH);
		} catch (VersionAlreadyExists | InexistentProject e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes the database.
	 */
	@After
	public void tearDown(){
		this.database = new HashMap<FilePath, Repository>();
	}
	
	/**
	 * Test adding a new file.
	 * @throws ProjectAlreadyExists 
	 */
	@Test
	public void testCreateProject() throws ProjectAlreadyExists{
		//Test adding a new file
		createProject(PROJECT2, PROJECT_DESCRIPTION);
	}
		
	/**
	 * Test adding an already existing file.
	 * @throws ProjectAlreadyExists 
	 */
	@Test(expected=ProjectAlreadyExists.class)
	public void testCreateProjectWhichAlreadyExists() throws ProjectAlreadyExists{
		createProject(PROJECT, PROJECT_DESCRIPTION);
	}
}
