package users.datatypes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Class for testing User.
 * @author Jaime
 *
 */
@RunWith(Parameterized.class)
public class TestUser {
	private final String ID;
	private final UserLevel USER_LEVEL;
	private final String PASSWORD;
	private final String PASSWORD2;
	
	private User user;
	
	public TestUser(final String iD, final UserLevel uSER_LEVEL, final String pASSWORD, final String PASSWORD2) {
		super();
		ID = iD;
		USER_LEVEL = uSER_LEVEL;
		PASSWORD = pASSWORD;
		this.PASSWORD2 = PASSWORD;
	}
	
	@SuppressWarnings("rawtypes")
	@Parameterized.Parameters
	public static Collection testCases() {
	      return Arrays.asList(new Object[][] {
	         {"userId", UserLevel.ADMIN, "password", "password2"}
	      });
	}
	
	/**
	 * Resets user field.
	 */
	@Before
	public void setUp(){
		user = new User(ID, PASSWORD, USER_LEVEL);
	}
	
	/**
	 * Tests that the password check functions correctly.
	 */
	@Test
	public void testPassword(){
		assertTrue(user.checkPassword(PASSWORD));
		assertFalse(user.checkPassword(PASSWORD2));
	}
}
