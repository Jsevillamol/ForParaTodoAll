package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import users.TestUserMain;
import users.datatypes.TestUser;
import users.subsystems.TestSessionManager;
import users.subsystems.TestUserDAO;
import files.TestFileMain;
import files.subsystems.TestFileDAO;

/**
 * Runs all tests in the test suite.
 * @author Jaime
 *
 */
public class TestRunner {
   public static void main(final String[] args) {
      final Result result = JUnitCore.runClasses(
    		  //Test suite
    		  //Component tests
    		  TestFileDAO.class,
    		  TestUser.class,
    		  TestUserDAO.class,
    		  TestSessionManager.class,
    		  //Integration test
    		  TestUserMain.class,
    		  TestFileMain.class
    		  );
      for (final Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      
      System.out.println(
    		  (result.wasSuccessful())
    		  	?"Test successful!" 
    		  	:"Test failed"
    		  );
   }
}