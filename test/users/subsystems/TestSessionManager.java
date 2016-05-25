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

import users.exceptions.UserException.SessionExpired;

/**
 * Class for testing SessionManager.
 * @author Jaime
 *
 */
@RunWith(Parameterized.class)
public class TestSessionManager extends SessionManager{
	
	/*
	 * Input data used through the test cases
	 */
	final String userId;
	final String userId2;
	int session;
	final int session2;
	
	public TestSessionManager(final String sessionId, final String sessionId2, final int session2) {
		super();
		this.userId = sessionId;
		this.userId2 = sessionId2;
		this.session2 = session2;
	}

	@SuppressWarnings("rawtypes")
	@Parameterized.Parameters
	public static Collection testCases() {
	      return Arrays.asList(new Object[][] {
	         {"userId1", "userId2", 1337}
	         //Add here input configurations you want to test
	      });
	}
	
	@Before
	public void setUp(){
		session = this.generateSession(userId);
	}
	
	@After
	public void tearDown(){
		this.sessions = new HashMap<>();
	}
	
	@Test
	public void testGetUser() throws SessionExpired{
		assertEquals(userId, this.getUser(session));
	}
	
	@Test(expected=SessionExpired.class)
	public void testGetUser2() throws SessionExpired{
		this.getUser(session2);
	}
}
