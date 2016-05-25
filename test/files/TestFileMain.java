package files;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import users.TestUserMain;
import files.subsystems.TestFileDAO;

/**
 * Class to test FileMain.
 * @author Jaime
 *
 */
public class TestFileMain extends FileMain {
	
	static FileMain reference;
	
	public static FileMain getReference() {
		if(reference == null){
			reference = new FileMain();
			reference.userSystem = TestUserMain.getReference();
			reference.fileDAO = TestFileDAO.getReference();
		}
		return reference;
	}
	@Test
	public void testTest(){
		assertTrue(true);
	}
	
	
}
