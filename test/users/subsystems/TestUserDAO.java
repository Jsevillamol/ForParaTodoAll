package users.subsystems;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import users.datatypes.User;
import users.datatypes.UserLevel;
import users.exceptions.UserException.UnknownUserException;

/**
 * Class for testing UserDAO.
 * When initialized using getReference adds a default admin to its database.
 * Unlike userDAO, this class is not a Singleton, to facilitate testing.
 * @author Jaime
 *
 */
@RunWith(Parameterized.class)
public class TestUserDAO extends UserDAO {
	/*
	 * Default info for default admin.
	 */
	static String ADMIN_NAME = "SysAdmin", ADMIN_PASSWORD = "CMF6WTtTLJ";
	
	/**
	 * Creates a UserDAO with a default user with admin privileges.
	 */
	public static IUserDAO getReference(){
		final User admin = new User(ADMIN_NAME, ADMIN_PASSWORD, UserLevel.ADMIN);
		final UserDAO instance = new UserDAO();
		instance.storeUser(admin);
		return instance;
	}
	
	/*
	 * Input data used through the test cases.
	 */
	final User oldUser;
	final User newUser;
	final String regex;
	
	public TestUserDAO(final String userId1, final String userId2, final String password1, 
			final String password2, final UserLevel level1, final UserLevel level2, final String regex){
		oldUser = new User(userId1, password1, level1);
		newUser = new User(userId2, password2, level2);
		this.regex = regex;
	}
	
	@SuppressWarnings("rawtypes")
	@Parameterized.Parameters
	public static Collection testCases() {
	      return Arrays.asList(new Object[][] {
	         {"userId1", "userId2", "password1", "password2", UserLevel.ADMIN, UserLevel.PROJECTLEADER, "regex"}
	         //Add here input configurations you want to test
	      });
	}
	
	/**
	 * Stores a user in the database.
	 */
	@Before
	public void setUp(){
		this.storeUser(oldUser);
	}
	
	/**
	 * Resets the database.
	 */
	@After
	public void tearDown(){
		dataBase = new HashMap<>();
	}
	
	@Test
	public void testGetUser() throws UnknownUserException{
		assertEquals("The user retrieved should match the key used!", oldUser,getUser(oldUser.getUserId()));
	}
	
	@Test(expected = UnknownUserException.class)
	public void testGetUserWithUnknownUser() throws UnknownUserException{
		getUser(newUser.getUserId());
	}
}
