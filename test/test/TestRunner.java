package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import users.datatypes.TestUser;
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
    		  TestFileDAO.class,
    		  TestUser.class
    		  //Here go classes
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